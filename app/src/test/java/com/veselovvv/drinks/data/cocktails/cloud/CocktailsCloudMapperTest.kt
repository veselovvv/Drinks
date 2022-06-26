package com.veselovvv.drinks.data.cocktails.cloud

import com.veselovvv.drinks.data.cocktails.CocktailData
import com.veselovvv.drinks.data.cocktails.ToCocktailMapper
import org.junit.Assert.assertEquals
import org.junit.Test

class CocktailsCloudMapperTest {
    @Test
    fun test_mapping() {
        val cocktailsCloudMapper = CocktailsCloudMapper.Base(ToCocktailMapper.Base())
        val data = listOf(
            CocktailCloud("1", "Margarita", "Ordinal", "https://somephotopath1"),
            CocktailCloud("12", "Martini", "Ordinal", "https://somephotopath2")
        )
        val expected = listOf(
            CocktailData("1", "Margarita", "Ordinal", "https://somephotopath1"),
            CocktailData("12", "Martini", "Ordinal", "https://somephotopath2")
        )
        val actual = cocktailsCloudMapper.map(data)
        assertEquals(expected, actual)
    }
}