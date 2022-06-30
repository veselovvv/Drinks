package com.veselovvv.drinks.data.cocktaildetails.cloud

import com.veselovvv.drinks.data.cocktaildetails.CocktailDetailsData
import com.veselovvv.drinks.data.cocktaildetails.ToCocktailDetailsMapper
import org.junit.Assert.assertEquals
import org.junit.Test

class CocktailDetailsCloudMapperTest {
    @Test
    fun test_mapping() {
        val cocktailsCloudMapper = CocktailDetailsCloudMapper.Base(ToCocktailDetailsMapper.Base())
        val cocktailDetails = CocktailDetailsCloud(
            "Margarita",
            "Alcoholic",
            "Cocktail glass",
            "Rub the rim of the glass with the lime slice to make the salt stick to it.",
            "Tequila", "Triple sec", "Lime juice", "Salt",
            null,null, null, null, null, null
        )
        val expected = CocktailDetailsData(
            "Alcoholic",
            "Cocktail glass",
            "Rub the rim of the glass with the lime slice to make the salt stick to it.",
            listOf("Tequila", "Triple sec", "Lime juice", "Salt", "", "", "", "", "", "")
        )
        val actual = cocktailsCloudMapper.map(cocktailDetails)
        assertEquals(expected, actual)
    }
}