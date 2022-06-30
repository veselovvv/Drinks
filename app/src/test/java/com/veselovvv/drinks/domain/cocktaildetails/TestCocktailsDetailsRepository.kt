package com.veselovvv.drinks.domain.cocktaildetails

import com.veselovvv.drinks.data.cocktaildetails.CocktailDetailsData
import com.veselovvv.drinks.data.cocktaildetails.CocktailDetailsRepository
import com.veselovvv.drinks.data.cocktaildetails.CocktailsDetailsData

class TestCocktailsDetailsRepository(private val exception: Exception? = null) : CocktailDetailsRepository {
    private val cocktailDetails = CocktailDetailsData(
        "Alcoholic",
        "Cocktail glass",
        "Rub the rim of the glass with the lime slice to make the salt stick to it.",
        listOf("Tequila", "Triple sec", "Lime juice", "Salt", "", "", "", "", "", "")
    )

    override suspend fun fetchCocktailDetails(cocktailName: String) =
        if (exception == null) CocktailsDetailsData.Success(cocktailDetails)
        else CocktailsDetailsData.Fail(exception)
}