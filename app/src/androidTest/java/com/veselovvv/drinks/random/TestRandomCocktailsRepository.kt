package com.veselovvv.drinks.random

import com.veselovvv.drinks.data.randomcocktail.RandomCocktailData
import com.veselovvv.drinks.data.randomcocktail.RandomCocktailRepository
import com.veselovvv.drinks.data.randomcocktail.RandomCocktailsData
import java.net.UnknownHostException

class TestRandomCocktailsRepository : RandomCocktailRepository {
    private var isSuccess = false

    private val randomCocktail = RandomCocktailData(
        "Margarita",
        "Cocktail",
        "Alcoholic",
        "Cocktail glass",
        "Rub the rim of the glass with the lime slice to make the salt stick to it.",
        "https://somephotopath1",
        listOf("Tequila", "Triple sec", "Lime juice", "Salt", "", "", "", "", "", "")
    )

    override suspend fun fetchRandomCocktail(): RandomCocktailsData {
        isSuccess = !isSuccess

        return if (isSuccess) RandomCocktailsData.Success(randomCocktail)
        else RandomCocktailsData.Fail(UnknownHostException())
    }
}