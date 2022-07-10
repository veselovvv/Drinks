package com.veselovvv.drinks.presentation.randomcocktail

import android.view.View
import android.view.ViewGroup
import com.google.android.material.button.MaterialButton
import com.google.android.material.textview.MaterialTextView
import com.veselovvv.drinks.core.Retry
import de.hdodenhof.circleimageview.CircleImageView

interface RandomCocktailUiMapper {
    fun map(progressLayout: ViewGroup)
    fun map(
        view: View,
        nameTextView: MaterialTextView,
        categoryTextView: MaterialTextView,
        alcoholicTextView: MaterialTextView,
        glassTextView: MaterialTextView,
        instructionsTextView: MaterialTextView,
        photoImageView: CircleImageView,
        ingredientsTextViews: List<MaterialTextView>
    )
    fun map(
        failLayout: ViewGroup,
        messageTextView: MaterialTextView,
        tryAgainButton: MaterialButton,
        retry: Retry
    )
}