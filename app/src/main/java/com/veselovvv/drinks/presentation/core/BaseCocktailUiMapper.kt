package com.veselovvv.drinks.presentation.core

import android.view.ViewGroup
import com.google.android.material.button.MaterialButton
import com.google.android.material.textview.MaterialTextView
import com.veselovvv.drinks.core.Retry

interface BaseCocktailUiMapper {
    fun map(progressLayout: ViewGroup)
    fun map(
        failLayout: ViewGroup,
        messageTextView: MaterialTextView,
        tryAgainButton: MaterialButton,
        retry: Retry
    )

    abstract class BaseCocktailUi : BaseCocktailUiMapper {
        override fun map(
            failLayout: ViewGroup,
            messageTextView: MaterialTextView,
            tryAgainButton: MaterialButton,
            retry: Retry
        ) = Unit

        protected fun mapIngredients(
            ingredients: List<String>, ingredientsTextViews: List<MaterialTextView>
        ) = ingredientsTextViews.forEachIndexed { index, textView ->
            if (ingredients[index] != "") textView.apply {
                show()
                text = ingredients[index]
            }
        }

        protected fun mapFail(
            failLayout: ViewGroup,
            messageTextView: MaterialTextView,
            tryAgainButton: MaterialButton,
            retry: Retry,
            message: String
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