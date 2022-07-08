package com.veselovvv.drinks.data.cocktails.cloud

import com.veselovvv.drinks.data.cocktails.CocktailData
import com.veselovvv.drinks.data.cocktails.ToCocktailMapper
import org.junit.Assert.assertEquals
import org.junit.Test

class CocktailsCloudMapperTest {
    @Test
    fun test_mapping() {
        val mapper = CocktailsCloudMapper.Base(ToCocktailMapper.Base())
        val cocktails = listOf(
            CocktailCloud("1", "Margarita", "Ordinal", "https://somephotopath1"),
            CocktailCloud("12", "Martini", "Ordinal", "https://somephotopath2")
        )
        val expected = listOf(
            CocktailData("1", "Margarita", "Ordinal", "https://somephotopath1"),
            CocktailData("12", "Martini", "Ordinal", "https://somephotopath2")
        )
        val actual = mapper.map(cocktails)
        assertEquals(expected, actual)
    }
}