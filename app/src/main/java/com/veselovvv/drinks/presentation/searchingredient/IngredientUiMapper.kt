package com.veselovvv.drinks.presentation.searchingredient

import android.view.ViewGroup
import com.google.android.material.button.MaterialButton
import com.google.android.material.textview.MaterialTextView

interface IngredientUiMapper {
    fun map(progressLayout: ViewGroup) // TODO dry (BaseCocktailUiMapper - make base thing for all the features)
    fun mapNoResults(noResultsLayout: ViewGroup)
    fun map(
        nameTextView: MaterialTextView,
        descriptionTextView: MaterialTextView,
        typeTextView: MaterialTextView,
        alcoholTextView: MaterialTextView,
        abvTextView: MaterialTextView
    )
    fun map(
        failLayout: ViewGroup,
        messageTextView: MaterialTextView,
        tryAgainButton: MaterialButton,
    )
}