package com.veselovvv.drinks.data.searchingredient.cloud

import org.junit.Assert.assertEquals
import org.junit.Test

class IngredientCloudMapperTest {
    @Test
    fun test_mapping() {
        val mapper = IngredientCloudMapper.Base(ToIngredientMapper.Base())
        val ingredient = IngredientCloud(
            "Fresh Mint",
            "Lamiaceae or Labiatae is a family of flowering plants commonly known as the mint or deadnettle family.",
            "Garnish",
            "No",
            null
        )
        val expected = IngredientData(
            "Fresh Mint",
            "Lamiaceae or Labiatae is a family of flowering plants commonly known as the mint or deadnettle family.",
            "Garnish",
            "No",
            ""
        )
        val actual = mapper.map(ingredient)
        assertEquals(expected, actual)
    }
}