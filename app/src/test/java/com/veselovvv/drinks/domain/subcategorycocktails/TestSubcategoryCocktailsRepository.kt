package com.veselovvv.drinks.domain.subcategorycocktails

import com.veselovvv.drinks.core.Category
import com.veselovvv.drinks.data.subcategorycocktails.SubcategoryCocktailData
import com.veselovvv.drinks.data.subcategorycocktails.SubcategoryCocktailsData
import com.veselovvv.drinks.data.subcategorycocktails.SubcategoryCocktailsRepository

class TestSubcategoryCocktailsRepository(
    private val exception: Exception? = null
) : SubcategoryCocktailsRepository {
    private val cocktails = listOf(
        SubcategoryCocktailData("Margarita", "https://somephotopath1"),
        SubcategoryCocktailData("Martini", "https://somephotopath2")
    )

    override suspend fun fetchCocktails(category: Category, subcategoryName: String) =
        if (exception == null) SubcategoryCocktailsData.Success(cocktails)
        else SubcategoryCocktailsData.Fail(exception)
}