package com.veselovvv.drinks

class CocktailsPage {
    private val recyclerViewUi = RecyclerViewUi()
    private val swipeToRefreshUi = SwipeToRefreshUi()

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
}
