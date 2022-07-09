package com.veselovvv.drinks.domain.randomcocktail

import com.veselovvv.drinks.TestResourceProvider
import com.veselovvv.drinks.core.ErrorType
import org.junit.Assert.assertEquals
import org.junit.Test

class RandomCocktailsDomainTest {
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
        val resultRandomCocktail = RandomCocktailDomain(
            "Margarita",
            "Cocktail",
            "Alcoholic",
            "Cocktail glass",
            "Rub the rim of the glass with the lime slice to make the salt stick to it.",
            "https://somephotopath1",
            listOf("Tequila", "Triple sec", "Lime juice", "Salt", "", "", "", "", "", "")
        )
        val randomCocktailDomainToUiMapper = BaseRandomCocktailDomainToUiMapper()
        val domain = RandomCocktailsDomain.Success(randomCocktail, BaseRandomCocktailDataToDomainMapper)
        val expected = RandomCocktailsUi.Success(resultRandomCocktail, randomCocktailDomainToUiMapper)
        val actual = domain.map(
            BaseRandomCocktailsDomainToUiMapper(
                TestResourceProvider(), randomCocktailDomainToUiMapper
            )
        )
        assertEquals(expected, actual)
    }

    @Test
    fun test_fail() {
        var domain = RandomCocktailsDomain.Fail(ErrorType.NO_CONNECTION)
        var expected = RandomCocktailsUi.Fail(NO_CONNECTION_MESSAGE)
        var actual = domain.map(
            BaseRandomCocktailsDomainToUiMapper(
                TestResourceProvider(), BaseRandomCocktailDomainToUiMapper()
            )
        )
        assertEquals(expected, actual)

        domain = RandomCocktailsDomain.Fail(ErrorType.SERVICE_UNAVAILABLE)
        expected = RandomCocktailsUi.Fail(SERVICE_UNAVAILABLE_MESSAGE)
        actual = domain.map(
            BaseRandomCocktailsDomainToUiMapper(
                TestResourceProvider(), BaseRandomCocktailDomainToUiMapper()
            )
        )
        assertEquals(expected, actual)
    }

    companion object {
        private const val NO_CONNECTION_MESSAGE = "No connection. Please try again!"
        private const val SERVICE_UNAVAILABLE_MESSAGE = "Service unavailable. Please try again!"
    }
}