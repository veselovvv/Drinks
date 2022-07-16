package com.veselovvv.drinks.domain.searchingredient

class TestIngredientRepository(private val exception: Exception? = null) : IngredientRepository {
    override suspend fun fetchIngredient(name: String) =
        if (exception == null) IngredientsData.Success(
            IngredientData(
                "Fresh Mint",
                "Lamiaceae or Labiatae is a family of flowering plants commonly known as the mint or deadnettle family.",
                "Garnish",
                "No",
                ""
            )
        ) else IngredientsData.Fail(exception)
}