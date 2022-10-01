package com.veselovvv.drinks.domain.subcategorycocktails

import com.veselovvv.drinks.data.subcategorycocktails.SubcategoryCocktailData
import com.veselovvv.drinks.data.subcategorycocktails.SubcategoryCocktailsData
import com.veselovvv.drinks.data.subcategorycocktails.SubcategoryCocktailsRepository

class TestSubcategoryCocktailsRepository(
    private val exception: Exception? = null
) : SubcategoryCocktailsRepository {
    private val cocktails = listOf(
        SubcategoryCocktailData("1", "Margarita", "https://somephotopath1"),
        SubcategoryCocktailData("12", "Martini", "https://somephotopath2")
    )

    override suspend fun fetchCocktails(categoryKey: String, subcategoryName: String) =
        if (exception == null) SubcategoryCocktailsData.Success(cocktails)
        else SubcategoryCocktailsData.Fail(exception)
}