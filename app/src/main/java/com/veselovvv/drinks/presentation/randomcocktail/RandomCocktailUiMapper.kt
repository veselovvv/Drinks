package com.veselovvv.drinks.presentation.randomcocktail

import android.view.View
import com.google.android.material.textview.MaterialTextView
import de.hdodenhof.circleimageview.CircleImageView

interface RandomCocktailUiMapper {
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
}