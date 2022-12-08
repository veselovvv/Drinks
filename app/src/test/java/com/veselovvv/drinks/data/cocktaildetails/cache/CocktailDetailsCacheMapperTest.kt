package com.veselovvv.drinks.data.cocktaildetails.cache

import com.veselovvv.drinks.data.cocktaildetails.CocktailDetailsData
import com.veselovvv.drinks.data.cocktaildetails.ToCocktailDetailsMapper
import org.junit.Assert.assertEquals
import org.junit.Test

class CocktailDetailsCacheMapperTest {
    @Test
    fun test_mapping() {
        val mapper = CocktailDetailsCacheMapper.Base(ToCocktailDetailsMapper.Base())
        val expected = CocktailDetailsData(
            "Margarita",
            "Alcoholic",
            "Cocktail glass",
            "Rub the rim of the glass with the lime slice to make the salt stick to it.",
            listOf("Tequila", "Triple sec", "Lime juice", "Salt", "", "", "", "", "", "")
        )
        val actual = mapper.map(
            CocktailDetailsDb(
                "Margarita",
                "Alcoholic",
                "Cocktail glass",
                "Rub the rim of the glass with the lime slice to make the salt stick to it.",
                "Tequila",
                "Triple sec",
                "Lime juice",
                "Salt",
                "", "", "", "", "", ""
            )
        )
        assertEquals(expected, actual)
    }
}