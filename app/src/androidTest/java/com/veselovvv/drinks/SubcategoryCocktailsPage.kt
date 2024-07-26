package com.veselovvv.drinks

class SubcategoryCocktailsPage : AbstractPage(R.id.subcategory_cocktails_root_layout) {
    private val recyclerViewUi = SubcategoryCocktailsRecyclerViewUi()
    private val swipeToRefreshUi = SwipeToRefreshUi(
        rootLayout,
        R.id.subcategory_cocktails_swipe_to_refresh
    )

    fun checkSubcategoryCocktailsListState(subcategoryCocktails: List<String>) =
        recyclerViewUi.checkSubcategoryCocktailsListState(subcategoryCocktails = subcategoryCocktails)

    fun swipeToRefresh() = swipeToRefreshUi.swipeToRefresh()
    fun checkErrorState(message: String) = recyclerViewUi.checkErrorState(message = message)
    fun clickTryAgainButton() = recyclerViewUi.clickTryAgainButton()
    fun clickOnItemInList(index: Int) = recyclerViewUi.clickOnItemInList(index)
}
