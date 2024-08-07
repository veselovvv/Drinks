package com.veselovvv.drinks.categories

import com.veselovvv.drinks.R
import com.veselovvv.drinks.core.AbstractPage
import com.veselovvv.drinks.core.SwipeToRefreshUi

class SubcategoriesPage : AbstractPage(R.id.subcategories_root_layout) {
    private val recyclerViewUi = SubcategoriesRecyclerViewUi()
    private val swipeToRefreshUi = SwipeToRefreshUi(
        rootLayout,
        R.id.subcategories_swipe_to_refresh
    )

    fun checkSubcategoriesListState(subcategories: List<String>) =
        recyclerViewUi.checkSubcategoriesListState(subcategories = subcategories)

    fun swipeToRefresh() = swipeToRefreshUi.swipeToRefresh()
    fun checkErrorState(message: String) = recyclerViewUi.checkErrorState(message = message)
    fun clickTryAgainButton() = recyclerViewUi.clickTryAgainButton()
    fun clickOnItemInList(index: Int) = recyclerViewUi.clickOnItemInList(index)
}
