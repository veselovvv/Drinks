package com.veselovvv.drinks.presentation.cocktails

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.veselovvv.drinks.core.Retry
import com.veselovvv.drinks.databinding.CocktailLayoutBinding
import com.veselovvv.drinks.databinding.FailFullscreenBinding
import com.veselovvv.drinks.databinding.ProgressFullscreenBinding

class CocktailsAdapter(
    private val retry: Retry,
    private val cocktailListener: CocktailListener
) : RecyclerView.Adapter<CocktailsAdapter.DrinksViewHolder>() {
    private val cocktails = ArrayList<CocktailUi>()

    fun update(newList: List<CocktailUi>) {
        cocktails.clear()
        cocktails.addAll(newList)
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int) = when (cocktails[position]) {
        is CocktailUi.Base -> 0
        is CocktailUi.Fail -> 1
        is CocktailUi.Progress -> 2
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = when (viewType) {
        0 -> DrinksViewHolder.Base(
            CocktailLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            cocktailListener
        )
        1 -> DrinksViewHolder.Fail(
            FailFullscreenBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            retry
        )
        else -> DrinksViewHolder.FullscreenProgress(
            ProgressFullscreenBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: DrinksViewHolder, position: Int) =
        holder.bind(cocktails[position])

    override fun getItemCount() = cocktails.size

    abstract class DrinksViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        open fun bind(cocktail: CocktailUi) = Unit

        class FullscreenProgress(binding: ProgressFullscreenBinding) : DrinksViewHolder(binding.root)

        class Base(
            private val binding: CocktailLayoutBinding,
            private val cocktailListener: CocktailListener
        ) : DrinksViewHolder(binding.root) {
            override fun bind(cocktail: CocktailUi) {
                cocktail.map(object : CocktailUi.BaseMapper {
                    override fun map(id: String, name: String, category: String, photoUrl: String) {
                        Glide.with(itemView).load(photoUrl).into(binding.cocktailPhotoImageView)
                        binding.cocktailNameTextView.text = name
                        binding.cocktailCategoryTextView.text = category
                    }
                    override fun map(text: String) = Unit
                })

                itemView.setOnClickListener {
                    cocktail.open(cocktailListener)
                }
            }
        }

        class Fail(
            private val binding: FailFullscreenBinding,
            private val retry: Retry
        ) : DrinksViewHolder(binding.root) {
            override fun bind(cocktail: CocktailUi) {
                cocktail.map(object : CocktailUi.BaseMapper {
                    override fun map(text: String) {
                        binding.failMessageTextView.text = text
                    }
                    override fun map(id: String, name: String, category: String, photoUrl: String) = Unit
                })
                binding.failTryAgainButton.setOnClickListener {
                    retry.tryAgain()
                }
            }
        }
    }

    interface CocktailListener {
        fun showCocktail(name: String, category: String, photoUrl: String)
    }
}