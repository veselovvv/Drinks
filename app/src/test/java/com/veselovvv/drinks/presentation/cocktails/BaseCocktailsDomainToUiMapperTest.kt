package com.veselovvv.drinks.presentation.cocktails

import com.veselovvv.drinks.TestResourceProvider
import com.veselovvv.drinks.core.ErrorType
import com.veselovvv.drinks.domain.cocktails.CocktailDomain
import org.junit.Assert.assertEquals
import org.junit.Test

class BaseCocktailsDomainToUiMapperTest {
    private val cocktailDomainToUiMapper = BaseCocktailDomainToUiMapper()
    private val mapper = BaseCocktailsDomainToUiMapper(
        TestResourceProvider(), cocktailDomainToUiMapper
    )

    @Test
    fun test_success() {
        val cocktails = listOf(
            CocktailDomain("Margarita", "Ordinal", "https://somephotopath1"),
            CocktailDomain("Martini", "Ordinal", "https://somephotopath2")
        )
        val expected = CocktailsUi.Success(cocktails, cocktailDomainToUiMapper)
        val actual = mapper.map(cocktails)
        assertEquals(expected, actual)
    }

    @Test
    fun test_fail() {
        var expected = CocktailsUi.Fail(NO_CONNECTION_MESSAGE)
        var actual = mapper.map(ErrorType.NO_CONNECTION)
        assertEquals(expected, actual)

        expected = CocktailsUi.Fail(SERVICE_UNAVAILABLE_MESSAGE)
        actual = mapper.map(ErrorType.SERVICE_UNAVAILABLE)
        assertEquals(expected, actual)
    }

    companion object {
        private const val NO_CONNECTION_MESSAGE = "No connection. Please try again!"
        private const val SERVICE_UNAVAILABLE_MESSAGE = "Service unavailable. Please try again!"
    }
}