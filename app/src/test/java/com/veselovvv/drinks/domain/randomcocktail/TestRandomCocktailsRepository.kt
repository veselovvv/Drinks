package com.veselovvv.drinks.domain.randomcocktail

class TestRandomCocktailsRepository(
    private val exception: Exception? = null
) : RandomCocktailsRepository {
    private val randomCocktail = RandomCocktailData(
        "Margarita",
        "Cocktail",
        "Alcoholic",
        "Cocktail glass",
        "Rub the rim of the glass with the lime slice to make the salt stick to it.",
        "https://somephotopath1",
        listOf("Tequila", "Triple sec", "Lime juice", "Salt", "", "", "", "", "", "")
    )

    override suspend fun fetchRandomCocktail() =
        if (exception == null) RandomCocktailsData.Success(randomCocktail)
        else RandomCocktailsData.Fail(exception)
}