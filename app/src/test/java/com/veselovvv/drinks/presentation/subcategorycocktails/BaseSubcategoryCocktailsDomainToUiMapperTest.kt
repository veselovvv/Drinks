package com.veselovvv.drinks.presentation.subcategorycocktails

import com.veselovvv.drinks.TestResourceProvider
import com.veselovvv.drinks.core.ErrorType
import com.veselovvv.drinks.domain.subcategorycocktails.SubcategoryCocktailDomain
import org.junit.Assert.assertEquals
import org.junit.Test

class BaseSubcategoryCocktailsDomainToUiMapperTest {
    private val subcategoryCocktailDomainToUiMapper = BaseSubcategoryCocktailDomainToUiMapper()
    private val mapper = BaseSubcategoryCocktailsDomainToUiMapper(
        TestResourceProvider(), subcategoryCocktailDomainToUiMapper
    )

    @Test
    fun test_success() {
        val cocktails = listOf(
            SubcategoryCocktailDomain("Margarita", "https://somephotopath1"),
            SubcategoryCocktailDomain("Martini", "https://somephotopath2")
        )
        val expected = SubcategoryCocktailsUi.Success(cocktails, subcategoryCocktailDomainToUiMapper)
        val actual = mapper.map(cocktails)
        assertEquals(expected, actual)
    }

    @Test
    fun test_fail() {
        var expected = SubcategoryCocktailsUi.Fail(NO_CONNECTION_MESSAGE)
        var actual = mapper.map(ErrorType.NO_CONNECTION)
        assertEquals(expected, actual)

        expected = SubcategoryCocktailsUi.Fail(SERVICE_UNAVAILABLE_MESSAGE)
        actual = mapper.map(ErrorType.SERVICE_UNAVAILABLE)
        assertEquals(expected, actual)
    }

    companion object {
        private const val NO_CONNECTION_MESSAGE = "No connection. Please try again!"
        private const val SERVICE_UNAVAILABLE_MESSAGE = "Service unavailable. Please try again!"
    }
}