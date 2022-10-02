package com.veselovvv.drinks.presentation.subcategorycocktails

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.veselovvv.drinks.core.Retry
import com.veselovvv.drinks.databinding.FailFullscreenBinding
import com.veselovvv.drinks.databinding.ProgressFullscreenBinding
import com.veselovvv.drinks.databinding.SubcategoryCocktailLayoutBinding
import com.veselovvv.drinks.presentation.core.loadImage

class SubcategoryCocktailsAdapter(
    private val retry: Retry,
    private val cocktailListener: CocktailListener
) : RecyclerView.Adapter<SubcategoryCocktailsAdapter.DrinksViewHolder>() {
    private val cocktails = ArrayList<SubcategoryCocktailUi>()

    fun update(newList: List<SubcategoryCocktailUi>) {
        cocktails.clear()
        cocktails.addAll(newList)
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int) = when (cocktails[position]) {
        is SubcategoryCocktailUi.Base -> 0
        is SubcategoryCocktailUi.Fail -> 1
        is SubcategoryCocktailUi.Progress -> 2
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = when (viewType) {
        0 -> DrinksViewHolder.Base(
            SubcategoryCocktailLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false),
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
        open fun bind(cocktail: SubcategoryCocktailUi) = Unit

        class FullscreenProgress(binding: ProgressFullscreenBinding) : DrinksViewHolder(binding.root)

        class Base(
            private val binding: SubcategoryCocktailLayoutBinding,
            private val cocktailListener: CocktailListener
        ) : DrinksViewHolder(binding.root) {
            override fun bind(cocktail: SubcategoryCocktailUi) {
                cocktail.map(object : SubcategoryCocktailUi.BaseMapper {
                    override fun map(id: String, name: String, photoUrl: String) {
                        // TODO why this method gets id? + check in other adapters if there are unused parameters
                        with(binding) {
                            subcategoryCocktailPhotoImageView.loadImage(itemView, photoUrl)
                            subcategoryCocktailNameTextView.text = name
                        }
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
            override fun bind(cocktail: SubcategoryCocktailUi) {
                cocktail.map(object : SubcategoryCocktailUi.BaseMapper {
                    override fun map(text: String) {
                        binding.failMessageTextView.text = text
                    }
                    override fun map(id: String, name: String, photoUrl: String) = Unit
                })
                binding.failTryAgainButton.setOnClickListener {
                    retry.tryAgain()
                }
            }
        }
    }

    interface CocktailListener {
        fun showCocktail(name: String, photoUrl: String)
    }
}