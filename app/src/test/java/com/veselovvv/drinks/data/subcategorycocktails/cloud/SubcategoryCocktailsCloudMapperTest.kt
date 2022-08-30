package com.veselovvv.drinks.data.subcategorycocktails.cloud

import org.junit.Assert.assertEquals
import org.junit.Test

class SubcategoryCocktailsCloudMapperTest {
    @Test
    fun test_mapping() {
        val mapper = SubcategoryCocktailsCloudMapper.Base(ToSubcategoryCocktailMapper.Base())
        val cocktails = listOf(
            SubcategoryCocktailCloud("1", "Margarita", "https://somephotopath1"),
            SubcategoryCocktailCloud("12", "Martini", "https://somephotopath2")
        )
        val expected = listOf(
            SubcategoryCocktailData("1", "Margarita", "https://somephotopath1"),
            SubcategoryCocktailData("12", "Martini", "https://somephotopath2")
        )
        val actual = mapper.map(cocktails)
        assertEquals(expected, actual)
    }
}