package com.veselovvv.drinks.ingredients

import com.veselovvv.drinks.data.searchingredient.IngredientData
import com.veselovvv.drinks.data.searchingredient.IngredientRepository
import com.veselovvv.drinks.data.searchingredient.IngredientsData
import java.net.UnknownHostException

class TestIngredientRepository : IngredientRepository {
    private var isSuccess = false

    override suspend fun fetchIngredient(name: String) =
        if (isSuccess) {
            if (name == "Fresh Mint") IngredientsData.Success(
                IngredientData(
                    "Fresh Mint",
                    "Lamiaceae or Labiatae is a family of flowering plants commonly known as the mint or deadnettle family.",
                    "Garnish",
                    "No",
                    ""
                )
            ) else IngredientsData.NoResults
        } else {
            isSuccess = true
            IngredientsData.Fail(UnknownHostException())
        }
}