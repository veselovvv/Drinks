package com.veselovvv.drinks.domain.randomcocktail

import com.veselovvv.drinks.core.ErrorType
import com.veselovvv.drinks.data.randomcocktail.RandomCocktailData
import org.junit.Assert.assertEquals
import org.junit.Test
import java.net.UnknownHostException

class BaseRandomCocktailsDataToDomainMapperTest {
    private val randomCocktailDataToDomainMapper = BaseRandomCocktailDataToDomainMapper()
    private val mapper = BaseRandomCocktailsDataToDomainMapper(randomCocktailDataToDomainMapper)

    @Test
    fun test_success() {
        val randomCocktail = RandomCocktailData(
            "Margarita",
            "Cocktail",
            "Alcoholic",
            "Cocktail glass",
            "Rub the rim of the glass with the lime slice to make the salt stick to it.",
            "https://somephotopath1",
            listOf("Tequila", "Triple sec", "Lime juice", "Salt", "", "", "", "", "", "")
        )
        val expected = RandomCocktailsDomain.Success(randomCocktail, randomCocktailDataToDomainMapper)
        val actual = mapper.map(randomCocktail)
        assertEquals(expected, actual)
    }

    @Test
    fun test_fail() {
        var expected = RandomCocktailsDomain.Fail(ErrorType.NO_CONNECTION)
        var actual = mapper.map(UnknownHostException())
        assertEquals(expected, actual)

        expected = RandomCocktailsDomain.Fail(ErrorType.GENERIC_ERROR)
        actual = mapper.map(Exception())
        assertEquals(expected, actual)
    }
}