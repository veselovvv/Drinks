package com.veselovvv.drinks.domain.subcategorycocktails

class TestSubcategoryCocktailsRepository(
    private val exception: Exception? = null
) : SubcategoryCocktailsRepository {
    private val cocktails = listOf(
        SubcategoryCocktailData("1", "Margarita", "https://somephotopath1"),
        SubcategoryCocktailData("12", "Martini", "https://somephotopath2")
    )

    override suspend fun fetchSubcategoryCocktails(subcategoryName: String) =
        if (exception == null) SubcategoryCocktailsData.Success(cocktails)
        else SubcategoryCocktailsData.Fail(exception)
}