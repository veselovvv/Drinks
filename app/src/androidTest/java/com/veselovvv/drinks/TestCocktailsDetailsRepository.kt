package com.veselovvv.drinks

import com.veselovvv.drinks.data.cocktaildetails.CocktailDetailsData
import com.veselovvv.drinks.data.cocktaildetails.CocktailDetailsRepository
import com.veselovvv.drinks.data.cocktaildetails.CocktailsDetailsData
import java.net.UnknownHostException

class TestCocktailsDetailsRepository : CocktailDetailsRepository {
    private val cocktailDetails = CocktailDetailsData(
        "Margarita",
        "Alcoholic",
        "Cocktail glass",
        "Rub the rim of the glass with the lime slice to make the salt stick to it.",
        listOf("Tequila", "Triple sec", "Lime juice", "Salt", "", "", "", "", "", "")
    )

    override suspend fun fetchCocktailDetails(cocktailName: String) =
        CocktailsDetailsData.Success(cocktailDetails)

    override suspend fun fetchCocktailDetailsFromNetwork(cocktailName: String) =
        CocktailsDetailsData.Fail(UnknownHostException())
}