package com.veselovvv.drinks.presentation.cocktaildetails

import android.view.ViewGroup
import com.google.android.material.button.MaterialButton
import com.google.android.material.textview.MaterialTextView
import com.veselovvv.drinks.core.Retry
import com.veselovvv.drinks.presentation.core.BaseCocktailUiMapper
import com.veselovvv.drinks.presentation.core.hide
import com.veselovvv.drinks.presentation.core.show

sealed class CocktailDetailsUi : CocktailDetailsUiMapper, BaseCocktailUiMapper.BaseCocktailUi() {
    override fun map(
        alcoholicTextView: MaterialTextView,
        glassTextView: MaterialTextView,
        instructionsTextView: MaterialTextView,
        ingredientsTextViews: List<MaterialTextView>
    ) = Unit

    object Progress : CocktailDetailsUi() {
        override fun map(progressLayout: ViewGroup) = progressLayout.show()
    }

    class Base(
        private val alcoholic: String,
        private val glass: String,
        private val instructions: String,
        private val ingredients: List<String>
    ) : CocktailDetailsUi() {
        override fun map(progressLayout: ViewGroup) = progressLayout.hide()
        override fun map(
            alcoholicTextView: MaterialTextView,
            glassTextView: MaterialTextView,
            instructionsTextView: MaterialTextView,
            ingredientsTextViews: List<MaterialTextView>
        ) {
            alcoholicTextView.text = alcoholic
            glassTextView.text = glass
            instructionsTextView.text = instructions
            mapIngredients(ingredients, ingredientsTextViews)
        }
    }

    class Fail(private val message: String) : CocktailDetailsUi() {
        override fun map(progressLayout: ViewGroup) = progressLayout.hide()
        override fun map(
            failLayout: ViewGroup,
            messageTextView: MaterialTextView,
            tryAgainButton: MaterialButton,
            retry: Retry
        ) = mapFail(failLayout, messageTextView, tryAgainButton, retry, message)
    }
}