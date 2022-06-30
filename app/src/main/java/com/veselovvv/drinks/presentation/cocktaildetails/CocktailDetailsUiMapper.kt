package com.veselovvv.drinks.presentation.cocktaildetails

import android.view.ViewGroup
import com.google.android.material.button.MaterialButton
import com.google.android.material.textview.MaterialTextView
import com.veselovvv.drinks.core.Retry

interface CocktailDetailsUiMapper {
    fun map(progressLayout: ViewGroup)
    fun map(
        alcoholicTextView: MaterialTextView,
        glassTextView: MaterialTextView,
        instructionsTextView: MaterialTextView,
        ingredientsTextViews: List<MaterialTextView>
    )
    fun map(
        failLayout: ViewGroup,
        messageTextView: MaterialTextView,
        tryAgainButton: MaterialButton,
        retry: Retry
    )
}