package com.veselovvv.drinks.presentation.cocktaildetails

import android.view.View
import android.view.ViewGroup
import com.google.android.material.button.MaterialButton
import com.google.android.material.textview.MaterialTextView
import com.veselovvv.drinks.core.Retry

sealed class CocktailDetailsUi : CocktailDetailsUiMapper {
    override fun map(
        alcoholicTextView: MaterialTextView,
        glassTextView: MaterialTextView,
        instructionsTextView: MaterialTextView,
        ingredientsTextViews: List<MaterialTextView>
    ) = Unit

    override fun map(
        failLayout: ViewGroup,
        messageTextView: MaterialTextView,
        tryAgainButton: MaterialButton,
        retry: Retry
    ) = Unit

    //TODO something with that?
    fun show(view: View, show: Boolean = true) {
        view.visibility = if (show) View.VISIBLE else View.GONE
    }

    object Progress : CocktailDetailsUi() {
        override fun map(progressLayout: ViewGroup) = show(progressLayout)
    }

    class Base(
        private val alcoholic: String,
        private val glass: String,
        private val instructions: String,
        private val ingredients: List<String>
    ) : CocktailDetailsUi() {
        override fun map(progressLayout: ViewGroup) = show(progressLayout, false)
        override fun map(
            alcoholicTextView: MaterialTextView,
            glassTextView: MaterialTextView,
            instructionsTextView: MaterialTextView,
            ingredientsTextViews: List<MaterialTextView>
        ) {
            alcoholicTextView.text = alcoholic
            glassTextView.text = glass
            instructionsTextView.text = instructions

            ingredientsTextViews.forEachIndexed { index, textView ->
                if (ingredients[index] != "") {
                    show(textView)
                    textView.text = ingredients[index]
                }
            }
        }
    }

    class Fail(private val message: String) : CocktailDetailsUi() {
        override fun map(progressLayout: ViewGroup) = show(progressLayout, false)
        override fun map(
            failLayout: ViewGroup,
            messageTextView: MaterialTextView,
            tryAgainButton: MaterialButton,
            retry: Retry
        ) {
            show(failLayout)
            messageTextView.text = message
            tryAgainButton.setOnClickListener {
                retry.tryAgain()
                show(failLayout, false)
            }
        }
    }
}