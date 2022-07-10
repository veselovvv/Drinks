package com.veselovvv.drinks.data.randomcocktail.cloud

import com.veselovvv.drinks.data.randomcocktail.RandomCocktailData
import com.veselovvv.drinks.data.randomcocktail.ToRandomCocktailMapper
import org.junit.Assert.assertEquals
import org.junit.Test

class RandomCocktailCloudMapperTest {
    @Test
    fun test_mapping() {
        val mapper = RandomCocktailCloudMapper.Base(ToRandomCocktailMapper.Base())
        val randomCocktail = RandomCocktailCloud(
            "Margarita",
            "Cocktail",
            "Alcoholic",
            "Cocktail glass",
            "Rub the rim of the glass with the lime slice to make the salt stick to it.",
            "https://somephotopath1",
            "Tequila", "Triple sec", "Lime juice", "Salt",
            null, null, null, null, null, null
        )
        val expected = RandomCocktailData(
            "Margarita",
            "Cocktail",
            "Alcoholic",
            "Cocktail glass",
            "Rub the rim of the glass with the lime slice to make the salt stick to it.",
            "https://somephotopath1",
            listOf("Tequila", "Triple sec", "Lime juice", "Salt", "", "", "", "", "", "")
        )
        val actual = mapper.map(randomCocktail)
        assertEquals(expected, actual)
    }
}