package com.veselovvv.drinks.presentation.cocktaildetails

import com.veselovvv.drinks.TestResourceProvider
import com.veselovvv.drinks.core.ErrorType
import com.veselovvv.drinks.domain.cocktaildetails.CocktailDetailsDomain
import org.junit.Assert.assertEquals
import org.junit.Test

class BaseCocktailsDetailsDomainToUiMapperTest {
    private val cocktailDetailsDomainToUiMapper = BaseCocktailDetailsDomainToUiMapper()
    private val mapper = BaseCocktailsDetailsDomainToUiMapper(
        TestResourceProvider(), cocktailDetailsDomainToUiMapper
    )

    @Test
    fun test_success() {
        val cocktailDetails = CocktailDetailsDomain(
            "Alcoholic",
            "Cocktail glass",
            "Rub the rim of the glass with the lime slice to make the salt stick to it.",
            listOf("Tequila", "Triple sec", "Lime juice", "Salt", "", "", "", "", "", "")
        )
        val expected = CocktailsDetailsUi.Success(cocktailDetails, cocktailDetailsDomainToUiMapper)
        val actual = mapper.map(cocktailDetails)
        assertEquals(expected, actual)
    }

    @Test
    fun test_fail() {
        var expected = CocktailsDetailsUi.Fail(NO_CONNECTION_MESSAGE)
        var actual = mapper.map(ErrorType.NO_CONNECTION)
        assertEquals(expected, actual)

        expected = CocktailsDetailsUi.Fail(SERVICE_UNAVAILABLE_MESSAGE)
        actual = mapper.map(ErrorType.SERVICE_UNAVAILABLE)
        assertEquals(expected, actual)
    }

    companion object {
        private const val NO_CONNECTION_MESSAGE = "No connection. Please try again!"
        private const val SERVICE_UNAVAILABLE_MESSAGE = "Service unavailable. Please try again!"
    }
}