package com.veselovvv.drinks.domain.cocktails

import com.veselovvv.drinks.TestResourceProvider
import com.veselovvv.drinks.core.ErrorType
import com.veselovvv.drinks.data.cocktails.CocktailData
import com.veselovvv.drinks.presentation.cocktails.BaseCocktailDomainToUiMapper
import com.veselovvv.drinks.presentation.cocktails.BaseCocktailsDomainToUiMapper
import com.veselovvv.drinks.presentation.cocktails.CocktailsUi
import org.junit.Assert.assertEquals
import org.junit.Test

class CocktailsDomainTest {
    @Test
    fun test_success() {
        val cocktails = listOf(
            CocktailData("1", "Margarita", "Ordinal", "https://somephotopath1"),
            CocktailData("12", "Martini", "Ordinal", "https://somephotopath2")
        )
        val resultCocktails = listOf(
            CocktailDomain("Margarita", "Ordinal", "https://somephotopath1"),
            CocktailDomain("Martini", "Ordinal", "https://somephotopath2")
        )
        val cocktailDomainToUiMapper = BaseCocktailDomainToUiMapper()
        val domain = CocktailsDomain.Success(cocktails, BaseCocktailDataToDomainMapper())
        val expected = CocktailsUi.Success(resultCocktails, cocktailDomainToUiMapper)
        val actual = domain.map(
            BaseCocktailsDomainToUiMapper(TestResourceProvider(), cocktailDomainToUiMapper)
        )
        assertEquals(expected, actual)
    }

    @Test
    fun test_fail() {
        var domain = CocktailsDomain.Fail(ErrorType.NO_CONNECTION)
        var expected = CocktailsUi.Fail(NO_CONNECTION_MESSAGE)
        var actual = domain.map(
            BaseCocktailsDomainToUiMapper(TestResourceProvider(), BaseCocktailDomainToUiMapper())
        )
        assertEquals(expected, actual)

        domain = CocktailsDomain.Fail(ErrorType.SERVICE_UNAVAILABLE)
        expected = CocktailsUi.Fail(SERVICE_UNAVAILABLE_MESSAGE)
        actual = domain.map(
            BaseCocktailsDomainToUiMapper(TestResourceProvider(), BaseCocktailDomainToUiMapper())
        )
        assertEquals(expected, actual)
    }

    companion object {
        private const val NO_CONNECTION_MESSAGE = "No connection. Please try again!"
        private const val SERVICE_UNAVAILABLE_MESSAGE = "Service unavailable. Please try again!"
    }
}