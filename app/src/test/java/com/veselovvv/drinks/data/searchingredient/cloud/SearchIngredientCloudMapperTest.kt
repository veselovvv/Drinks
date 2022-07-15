package com.veselovvv.drinks.data.searchingredient.cloud

import org.junit.Assert.assertEquals
import org.junit.Test

class SearchIngredientCloudMapperTest {
    @Test
    fun test_mapping() {
        val mapper = SearchIngredientCloudMapper.Base(ToSearchIngredientMapper.Base())
        val searchIngredient = SearchIngredientCloud(
            "Fresh Mint",
            "Lamiaceae or Labiatae is a family of flowering plants commonly known as the mint or deadnettle family.",
            "Garnish",
            "No",
            null
        )
        val expected = SearchIngredientData(
            "Fresh Mint",
            "Lamiaceae or Labiatae is a family of flowering plants commonly known as the mint or deadnettle family.",
            "Garnish",
            "No",
            ""
        )
        val actual = mapper.map(searchIngredient)
        assertEquals(expected, actual)
    }
}