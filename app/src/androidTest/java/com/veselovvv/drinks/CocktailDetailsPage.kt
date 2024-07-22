package com.veselovvv.drinks

class CocktailDetailsPage : AbstractPage(R.id.cocktail_details_root_layout) {
    private val swipeToRefreshUi = SwipeToRefreshUi(
        R.id.cocktail_details_root_layout,
        R.id.cocktail_details_swipe_to_refresh
    )
    private val errorUi = ErrorUi()

    fun checkCocktailDetailsState(
        name: String,
        category: String,
        alcoholic: String,
        glass: String,
        instructions: String,
        ingredient1: String,
        ingredient2: String,
        ingredient3: String,
        ingredient4: String
    ) {
        checkTextViewHasText(R.id.cocktail_details_name, name)
        checkTextViewHasText(R.id.cocktail_details_category, category)
        checkTextViewHasText(R.id.cocktail_details_alcoholic, alcoholic)
        checkTextViewHasText(R.id.cocktail_details_glass, glass)
        checkTextViewHasText(R.id.cocktail_details_instructions, instructions)
        checkTextViewHasText(R.id.cocktail_details_ingredient1, ingredient1)
        checkTextViewHasText(R.id.cocktail_details_ingredient2, ingredient2)
        checkTextViewHasText(R.id.cocktail_details_ingredient3, ingredient3)
        checkTextViewHasText(R.id.cocktail_details_ingredient4, ingredient4)
    }

    fun swipeToRefresh() = swipeToRefreshUi.swipeToRefresh()
    fun checkErrorState(message: String) = errorUi.checkErrorState(message)
    fun clickTryAgainButton() = errorUi.clickTryAgainButton()
}
