package com.veselovvv.drinks

class CocktailsPage : AbstractPage(R.id.cocktails_root_layout) {
    private val recyclerViewUi = RecyclerViewUi()
    private val searchViewUi = SearchViewUi()
    private val swipeToRefreshUi = SwipeToRefreshUi(
        R.id.cocktails_root_layout,
        R.id.cocktails_swipe_to_refresh
    )

    fun checkCocktailsListState(cocktails: List<Pair<String, String>>) =
        recyclerViewUi.checkCocktailsListState(cocktails = cocktails)

    fun swipeToRefresh() = swipeToRefreshUi.swipeToRefresh()
    fun checkErrorState(message: String) = recyclerViewUi.checkErrorState(message = message)
    fun clickTryAgainButton() = recyclerViewUi.clickTryAgainButton()
    fun clickSearchButton() = searchViewUi.clickSearchButton()
    fun checkSearchViewState() = searchViewUi.checkSearchViewState()
    fun clickBackSearchButton() = searchViewUi.clickBackSearchButton()
    fun typeInSearchView(text: String) = searchViewUi.typeInSearchView(text = text)
    fun checkNoResultsState(text: String) = recyclerViewUi.checkNoResultsState(text = text)
    fun clickOnItemInList(index: Int) = recyclerViewUi.clickOnItemInList(index)
}
