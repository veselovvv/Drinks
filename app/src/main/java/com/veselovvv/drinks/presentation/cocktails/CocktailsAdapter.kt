package com.veselovvv.drinks.presentation.cocktails

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.button.MaterialButton
import com.google.android.material.textview.MaterialTextView
import com.veselovvv.drinks.R
import com.veselovvv.drinks.core.Retry
import de.hdodenhof.circleimageview.CircleImageView

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
        0 -> DrinksViewHolder.Base(R.layout.cocktail_layout.makeView(parent), cocktailListener)
        1 -> DrinksViewHolder.Fail(R.layout.fail_fullscreen.makeView(parent), retry)
        else -> DrinksViewHolder.FullscreenProgress(R.layout.progress_fullscreen.makeView(parent))
    }

    override fun onBindViewHolder(holder: DrinksViewHolder, position: Int) =
        holder.bind(cocktails[position])

    override fun getItemCount() = cocktails.size

    abstract class DrinksViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        open fun bind(cocktail: CocktailUi) = Unit

        class FullscreenProgress(view: View) : DrinksViewHolder(view)

        class Base(view: View, private val cocktailListener: CocktailListener) : DrinksViewHolder(view) {
            private val photoImageView = itemView.findViewById<CircleImageView>(R.id.cocktail_photo_image_view)
            private val nameTextView = itemView.findViewById<MaterialTextView>(R.id.cocktail_name_text_view)
            private val categoryTextView = itemView.findViewById<MaterialTextView>(R.id.cocktail_category_text_view)

            override fun bind(cocktail: CocktailUi) {
                cocktail.map(object : CocktailUi.BaseMapper {
                    override fun map(id: String, name: String, category: String, photoUrl: String) {
                        Glide.with(itemView).load(photoUrl).into(photoImageView)
                        nameTextView.text = name
                        categoryTextView.text = category
                    }
                    override fun map(text: String) = Unit
                })

                itemView.setOnClickListener {
                    cocktail.open(cocktailListener)
                }
            }
        }

        class Fail(view: View, private val retry: Retry) : DrinksViewHolder(view) {
            private val messageTextView = itemView.findViewById<MaterialTextView>(R.id.fail_message_text_view)
            private val tryAgainButton = itemView.findViewById<MaterialButton>(R.id.fail_try_again_button)

            override fun bind(cocktail: CocktailUi) {
                cocktail.map(object : CocktailUi.BaseMapper {
                    override fun map(text: String) {
                        messageTextView.text = text
                    }
                    override fun map(id: String, name: String, category: String, photoUrl: String) = Unit
                })
                tryAgainButton.setOnClickListener { retry.tryAgain() }
            }
        }
    }

    interface CocktailListener {
        fun showCocktail(name: String, category: String, photoUrl: String)
    }
}

private fun Int.makeView(parent: ViewGroup) =
    LayoutInflater.from(parent.context).inflate(this, parent, false)