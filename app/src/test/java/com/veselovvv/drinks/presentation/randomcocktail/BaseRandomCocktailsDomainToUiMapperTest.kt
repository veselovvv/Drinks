package com.veselovvv.drinks.presentation.randomcocktail

import com.veselovvv.drinks.TestResourceProvider
import com.veselovvv.drinks.core.ErrorType
import com.veselovvv.drinks.domain.randomcocktail.RandomCocktailDomain
import org.junit.Assert.assertEquals
import org.junit.Test

class BaseRandomCocktailsDomainToUiMapperTest {
    private val randomCocktailDomainToUiMapper = BaseRandomCocktailDomainToUiMapper()
    private val mapper = BaseRandomCocktailsDomainToUiMapper(
        TestResourceProvider(), randomCocktailDomainToUiMapper
    )

    @Test
    fun test_success() {
        val randomCocktail = RandomCocktailDomain(
            "Margarita",
            "Cocktail",
            "Alcoholic",
            "Cocktail glass",
            "Rub the rim of the glass with the lime slice to make the salt stick to it.",
            "https://somephotopath1",
            listOf("Tequila", "Triple sec", "Lime juice", "Salt", "", "", "", "", "", "")
        )
        val expected = RandomCocktailsUi.Success(randomCocktail, randomCocktailDomainToUiMapper)
        val actual = mapper.map(randomCocktail)
        assertEquals(expected, actual)
    }

    @Test
    fun test_fail() {
        var expected = RandomCocktailsUi.Fail(NO_CONNECTION_MESSAGE)
        var actual = mapper.map(ErrorType.NO_CONNECTION)
        assertEquals(expected, actual)

        expected = RandomCocktailsUi.Fail(SERVICE_UNAVAILABLE_MESSAGE)
        actual = mapper.map(ErrorType.SERVICE_UNAVAILABLE)
        assertEquals(expected, actual)
    }

    companion object {
        private const val NO_CONNECTION_MESSAGE = "No connection. Please try again!"
        private const val SERVICE_UNAVAILABLE_MESSAGE = "Service unavailable. Please try again!"
    }
}