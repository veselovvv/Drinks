package com.veselovvv.drinks

class CocktailsPage {
    private val recyclerViewUi = RecyclerViewUi()
    private val swipeToRefreshUi = SwipeToRefreshUi()
    private val searchViewUi = SearchViewUi()

    fun checkCocktailsListState(cocktails: List<Pair<String, String>>) {
        recyclerViewUi.checkCocktailsListState(cocktails = cocktails)
    }

    fun swipeToRefresh() {
        swipeToRefreshUi.swipeToRefresh()
    }

    fun checkErrorState(message: String) {
        recyclerViewUi.checkErrorState(message = message)
    }

    fun clickTryAgainButton() {
        recyclerViewUi.clickTryAgainButton()
    }

    fun clickSearchButton() {
        searchViewUi.clickSearchButton()
    }

    fun checkSearchViewState() {
        searchViewUi.checkSearchViewState()
    }

    fun clickBackSearchButton() {
        searchViewUi.clickBackSearchButton()
    }

    fun typeInSearchView(text: String) {
        searchViewUi.typeInSearchView(text = text)
    }

    fun checkNoResultsState(text: String) {
        recyclerViewUi.checkNoResultsState(text = text)
    }
}
