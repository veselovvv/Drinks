package com.veselovvv.drinks.domain.searchingredient

import com.veselovvv.drinks.data.searchingredient.IngredientData
import com.veselovvv.drinks.data.searchingredient.IngredientRepository
import com.veselovvv.drinks.data.searchingredient.IngredientsData

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