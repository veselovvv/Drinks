package com.veselovvv.drinks

import com.veselovvv.drinks.data.cocktails.CocktailData
import com.veselovvv.drinks.data.cocktails.CocktailsData
import com.veselovvv.drinks.data.cocktails.CocktailsRepository
import java.net.UnknownHostException

class TestCocktailsRepository : CocktailsRepository {
    private val cocktails = listOf(
        CocktailData("1", "Margarita", "Ordinal", "https://somephotopath1"),
        CocktailData("12", "Martini", "Ordinal", "https://somephotopath2"),
        CocktailData("23", "Clover Club", "Ordinary Drink", "https://somephotopath2")
    )

    private val foundCocktails = listOf(
        CocktailData("12", "Martini", "Ordinal", "https://somephotopath2")
    )

    override suspend fun fetchCocktails() = CocktailsData.Success(cocktails)
    override suspend fun fetchCocktailsFromNetwork() = CocktailsData.Fail(UnknownHostException())
    override suspend fun searchCocktails(query: String) =
        CocktailsData.Success(
            when {
                query == "" -> cocktails
                query.startsWith("Mart") -> foundCocktails
                else -> listOf()
            }
        )
}