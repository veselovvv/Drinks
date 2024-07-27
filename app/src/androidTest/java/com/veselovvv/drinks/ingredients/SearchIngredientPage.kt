package com.veselovvv.drinks.ingredients

import com.veselovvv.drinks.R
import com.veselovvv.drinks.core.AbstractPage
import com.veselovvv.drinks.core.ErrorUi
import com.veselovvv.drinks.core.SearchViewUi

class SearchIngredientPage : AbstractPage(R.id.search_ingredient_root_layout) {
    private val searchViewUi = SearchViewUi()
    private val errorUi = ErrorUi(R.id.search_ingredient_fail_layout)
    private val noResultsUi = NoResultsUi()

    fun checkInitialState(text: String) =
        checkTextViewHasText(R.id.click_on_the_search_button_text_view, text)

    fun clickSearchButton() = searchViewUi.clickSearchButton()
    fun checkSearchViewState() = searchViewUi.checkSearchViewState()
    fun clickBackSearchButton() = searchViewUi.clickBackSearchButton()
    fun typeInSearchView(text: String) = searchViewUi.typeInSearchView(text)
    fun clickSearchButtonOnKeyboard() = searchViewUi.clickSearchButtonOnKeyboard()
    fun checkErrorState(message: String) = errorUi.checkErrorState(message)
    fun checkNoResultsState(text: String) = noResultsUi.checkNoResultsState(text)

    fun checkIngredientState(
        name: String,
        type: String,
        alcohol: String,
        abv: String,
        description: String
    ) {
        checkTextViewHasText(R.id.search_ingredient_name, name)
        checkTextViewHasText(R.id.search_ingredient_type, type)
        checkTextViewHasText(R.id.search_ingredient_alcohol, alcohol)
        checkTextViewHasText(R.id.search_ingredient_abv, abv)
        checkTextViewHasText(R.id.search_ingredient_description, description)
    }
}
