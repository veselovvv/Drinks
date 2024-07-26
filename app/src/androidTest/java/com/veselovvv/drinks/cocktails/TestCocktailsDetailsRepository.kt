package com.veselovvv.drinks.cocktails

import com.veselovvv.drinks.data.cocktaildetails.CocktailDetailsData
import com.veselovvv.drinks.data.cocktaildetails.CocktailDetailsRepository
import com.veselovvv.drinks.data.cocktaildetails.CocktailsDetailsData
import java.net.UnknownHostException

class TestCocktailsDetailsRepository : CocktailDetailsRepository {
    override suspend fun fetchCocktailDetails(cocktailName: String): CocktailsDetailsData =
        CocktailsDetailsData.Success(
            when (cocktailName) {
                "410 Gone" -> CocktailDetailsData(
                    "410 Gone",
                    "Alcoholic",
                    "Collins glass",
                    "",
                    listOf("Peach Vodka", "Coca-Cola", "", "", "", "", "", "", "", "")
                )
                "A. J." -> CocktailDetailsData(
                    "A. J.",
                    "Alcoholic",
                    "Cocktail glass",
                    "Shake ingredients with ice, strain into a cocktail glass, and serve.",
                    listOf("Applejack", "Grapefruit juice", "", "", "", "", "", "", "", "")
                )
                "Ace" -> CocktailDetailsData(
                    "Ace",
                    "Alcoholic",
                    "Martini glass",
                    "Shake all the ingredients in a cocktail shaker and ice then strain in a cold glass.",
                    listOf("Gin", "Grenadine", "Heavy cream", "Milk", "", "", "", "", "", "")
                )
                "3 Wise Men" -> CocktailDetailsData(
                    "3 Wise Men",
                    "Alcoholic",
                    "Collins glass",
                    "Put them in a glass and slam it to the head.",
                    listOf("Jack Daniels", "Johnnie Walker", "Jim Beam", "", "", "", "", "", "", "")
                )
                else -> CocktailDetailsData(
                    "Margarita",
                    "Alcoholic",
                    "Cocktail glass",
                    "Rub the rim of the glass with the lime slice to make the salt stick to it.",
                    listOf("Tequila", "Triple sec", "Lime juice", "Salt", "", "", "", "", "", "")
                )
            }
        )

    override suspend fun fetchCocktailDetailsFromNetwork(cocktailName: String) =
        CocktailsDetailsData.Fail(UnknownHostException())
}