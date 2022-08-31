package com.veselovvv.drinks.domain.subcategorycocktails

import com.veselovvv.drinks.TestResourceProvider
import com.veselovvv.drinks.core.ErrorType
import org.junit.Assert.assertEquals
import org.junit.Test

class SubcategoryCocktailsDomainTest {
    @Test
    fun test_success() {
        val cocktails = listOf(
            SubcategoryCocktailData("1", "Margarita", "https://somephotopath1"),
            SubcategoryCocktailData("12", "Martini", "https://somephotopath2")
        )
        val resultCocktails = listOf(
            SubcategoryCocktailDomain("1", "Margarita", "https://somephotopath1"),
            SubcategoryCocktailDomain("12", "Martini", "https://somephotopath2")
        )
        val subcategoryCocktailDomainToUiMapper = BaseSubcategoryCocktailDomainToUiMapper()
        val domain = SubcategoryCocktailsDomain.Success(
            cocktails, BaseSubcategoryCocktailDataToDomainMapper()
        )
        val expected = SubcategoryCocktailsUi.Success(
            resultCocktails, subcategoryCocktailDomainToUiMapper
        )
        val actual = domain.map(
            BaseSubcategoryCocktailsDomainToUiMapper(
                TestResourceProvider(), subcategoryCocktailDomainToUiMapper
            )
        )
        assertEquals(expected, actual)
    }

    @Test
    fun test_fail() {
        var domain = SubcategoryCocktailsDomain.Fail(ErrorType.NO_CONNECTION)
        var expected = SubcategoryCocktailsUi.Fail(NO_CONNECTION_MESSAGE)
        var actual = domain.map(
            BaseSubcategoryCocktailsDomainToUiMapper(
                TestResourceProvider(), BaseSubcategoryCocktailDomainToUiMapper()
            )
        )
        assertEquals(expected, actual)

        domain = SubcategoryCocktailsDomain.Fail(ErrorType.SERVICE_UNAVAILABLE)
        expected = SubcategoryCocktailsUi.Fail(SERVICE_UNAVAILABLE_MESSAGE)
        actual = domain.map(
            BaseSubcategoryCocktailsDomainToUiMapper(
                TestResourceProvider(), BaseSubcategoryCocktailDomainToUiMapper()
            )
        )
        assertEquals(expected, actual)
    }

    companion object {
        private const val NO_CONNECTION_MESSAGE = "No connection. Please try again!"
        private const val SERVICE_UNAVAILABLE_MESSAGE = "Service unavailable. Please try again!"
    }
}