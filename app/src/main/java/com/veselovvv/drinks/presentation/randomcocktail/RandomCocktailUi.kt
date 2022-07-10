package com.veselovvv.drinks.presentation.randomcocktail

import android.view.View
import android.view.ViewGroup
import com.google.android.material.button.MaterialButton
import com.google.android.material.textview.MaterialTextView
import com.veselovvv.drinks.core.Retry
import com.veselovvv.drinks.presentation.core.hide
import com.veselovvv.drinks.presentation.core.loadImage
import com.veselovvv.drinks.presentation.core.show
import de.hdodenhof.circleimageview.CircleImageView

sealed class RandomCocktailUi : RandomCocktailUiMapper {
    override fun map(
        view: View,
        nameTextView: MaterialTextView,
        categoryTextView: MaterialTextView,
        alcoholicTextView: MaterialTextView,
        glassTextView: MaterialTextView,
        instructionsTextView: MaterialTextView,
        photoImageView: CircleImageView,
        ingredientsTextViews: List<MaterialTextView>
    ) = Unit

    override fun map(
        failLayout: ViewGroup,
        messageTextView: MaterialTextView,
        tryAgainButton: MaterialButton,
        retry: Retry
    ) = Unit

    object Progress : RandomCocktailUi() {
        override fun map(progressLayout: ViewGroup) = progressLayout.show()
    }

    class Base(
        private val name: String,
        private val category: String,
        private val alcoholic: String,
        private val glass: String,
        private val instructions: String,
        private val photoUrl: String,
        private val ingredients: List<String>
    ) : RandomCocktailUi() {
        override fun map(progressLayout: ViewGroup) = progressLayout.hide()
        override fun map(
            view: View,
            nameTextView: MaterialTextView,
            categoryTextView: MaterialTextView,
            alcoholicTextView: MaterialTextView,
            glassTextView: MaterialTextView,
            instructionsTextView: MaterialTextView,
            photoImageView: CircleImageView,
            ingredientsTextViews: List<MaterialTextView>
        ) {
            nameTextView.text = name
            categoryTextView.text = category
            alcoholicTextView.text = alcoholic
            glassTextView.text = glass
            instructionsTextView.text = instructions
            photoImageView.loadImage(view, photoUrl)

            ingredientsTextViews.forEachIndexed { index, textView ->
                if (ingredients[index] != "") textView.apply {
                    show()
                    text = ingredients[index]
                }
            }
        }
    }

    //TODO fix DRY with generics here and in whole app:
    class Fail(private val message: String) : RandomCocktailUi() {
        override fun map(progressLayout: ViewGroup) = progressLayout.hide()
        override fun map(
            failLayout: ViewGroup,
            messageTextView: MaterialTextView,
            tryAgainButton: MaterialButton,
            retry: Retry
        ) {
            failLayout.show()
            messageTextView.text = message
            tryAgainButton.setOnClickListener {
                retry.tryAgain()
                failLayout.hide()
            }
        }
    }
}