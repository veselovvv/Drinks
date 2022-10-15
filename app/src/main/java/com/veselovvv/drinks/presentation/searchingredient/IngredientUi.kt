package com.veselovvv.drinks.presentation.searchingredient

import android.view.ViewGroup
import com.google.android.material.button.MaterialButton
import com.google.android.material.textview.MaterialTextView
import com.veselovvv.drinks.presentation.core.hide
import com.veselovvv.drinks.presentation.core.show

sealed class IngredientUi : IngredientUiMapper {
    override fun map(progressLayout: ViewGroup) = progressLayout.hide()
    override fun mapNoResults(noResultsLayout: ViewGroup) = noResultsLayout.hide()

    override fun map(
        startLayout: ViewGroup,
        nameTextView: MaterialTextView,
        descriptionTextView: MaterialTextView,
        typeTextView: MaterialTextView,
        alcoholTextView: MaterialTextView,
        abvTextView: MaterialTextView
    ) = Unit

    override fun map(
        failLayout: ViewGroup,
        messageTextView: MaterialTextView,
        tryAgainButton: MaterialButton,
    ) = Unit

    object Progress : IngredientUi() {
        override fun map(progressLayout: ViewGroup) = progressLayout.show()
    }

    class Base(
        private val name: String,
        private val description: String,
        private val type: String,
        private val alcohol: String,
        private val abv: String
    ) : IngredientUi() {
        override fun map(
            startLayout: ViewGroup,
            nameTextView: MaterialTextView,
            descriptionTextView: MaterialTextView,
            typeTextView: MaterialTextView,
            alcoholTextView: MaterialTextView,
            abvTextView: MaterialTextView
        ) {
            startLayout.hide()
            nameTextView.text = name
            descriptionTextView.text = description
            typeTextView.text = type
            alcoholTextView.text = alcohol
            abvTextView.text = if (abv == "") NO_DATA else abv
        }
    }

    class Fail(private val message: String) : IngredientUi() {
        override fun map(
            failLayout: ViewGroup,
            messageTextView: MaterialTextView,
            tryAgainButton: MaterialButton
        ) {
            failLayout.show()
            messageTextView.text = message
            tryAgainButton.hide()
        }
    }

    object NoResults : IngredientUi() {
        override fun mapNoResults(noResultsLayout: ViewGroup) = noResultsLayout.show()
    }

    companion object {
        private const val NO_DATA = "-"
    }
}