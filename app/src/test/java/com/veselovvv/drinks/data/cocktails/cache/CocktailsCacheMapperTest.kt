package com.veselovvv.drinks.data.cocktails.cache

import com.veselovvv.drinks.data.cocktails.CocktailData
import com.veselovvv.drinks.data.cocktails.ToCocktailMapper
import org.junit.Assert.assertEquals
import org.junit.Test

class CocktailsCacheMapperTest {
    @Test
    fun test_mapping() {
        val mapper = CocktailsCacheMapper.Base(ToCocktailMapper.Base())
        val expected = listOf(
            CocktailData("1", "Margarita", "Ordinal", "https://somephotopath1"),
            CocktailData("12", "Martini", "Ordinal", "https://somephotopath2")
        )
        val actual = mapper.map(
            listOf(
                CocktailDb("1", "Margarita", "Ordinal", "https://somephotopath1"),
                CocktailDb("12", "Martini", "Ordinal", "https://somephotopath2")
            )
        )
        assertEquals(expected, actual)
    }
}