package com.veselovvv.drinks.domain.cocktails

import com.veselovvv.drinks.data.cocktails.CocktailData
import com.veselovvv.drinks.data.cocktails.CocktailsData
import com.veselovvv.drinks.data.cocktails.CocktailsRepository

class TestCocktailsRepository(private val exception: Exception? = null) : CocktailsRepository {
    private val cocktails = listOf(
        CocktailData("1", "Margarita", "Ordinal", "https://somephotopath1"),
        CocktailData("12", "Martini", "Ordinal", "https://somephotopath2")
    )

    private val foundCocktails = listOf(
        CocktailData("12", "Martini", "Ordinal", "https://somephotopath2")
    )

    override suspend fun fetchCocktails() =
        if (exception == null) CocktailsData.Success(cocktails) else CocktailsData.Fail(exception)

    override suspend fun fetchCocktailsFromNetwork() =
        if (exception == null) CocktailsData.Success(cocktails) else CocktailsData.Fail(exception)

    override suspend fun searchCocktails(query: String) =
        if (exception == null) CocktailsData.Success(foundCocktails) else CocktailsData.Fail(exception)
}