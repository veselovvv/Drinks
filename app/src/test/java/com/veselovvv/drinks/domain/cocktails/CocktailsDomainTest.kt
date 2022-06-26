package com.veselovvv.drinks.domain.cocktails

import com.veselovvv.drinks.R
import com.veselovvv.drinks.core.ErrorType
import com.veselovvv.drinks.core.ResourceProvider
import com.veselovvv.drinks.data.cocktails.CocktailData
import com.veselovvv.drinks.presentation.cocktails.BaseCocktailDomainToUiMapper
import com.veselovvv.drinks.presentation.cocktails.BaseCocktailsDomainToUiMapper
import com.veselovvv.drinks.presentation.cocktails.CocktailsUi
import org.junit.Assert.assertEquals
import org.junit.Test

class CocktailsDomainTest {
    @Test
    fun test_success() {
        val data = listOf(
            CocktailData("1", "Margarita", "Ordinal", "https://somephotopath1"),
            CocktailData("12", "Martini", "Ordinal", "https://somephotopath2")
        )
        val resultData = listOf(
            CocktailDomain("1", "Margarita", "Ordinal", "https://somephotopath1"),
            CocktailDomain("12", "Martini", "Ordinal", "https://somephotopath2")
        )
        val cocktailDomainToUiMapper = BaseCocktailDomainToUiMapper()
        val successCocktailsDomain = CocktailsDomain.Success(data, BaseCocktailDataToDomainMapper())
        val expected = CocktailsUi.Success(resultData, cocktailDomainToUiMapper)
        val actual = successCocktailsDomain.map(
            BaseCocktailsDomainToUiMapper(TestResourceProvider(), cocktailDomainToUiMapper)
        )
        assertEquals(expected, actual)
    }

    @Test
    fun test_fail() {
        var failCocktailsDomain = CocktailsDomain.Fail(ErrorType.NO_CONNECTION)
        var expected = CocktailsUi.Fail(NO_CONNECTION_MESSAGE)
        var actual = failCocktailsDomain.map(
            BaseCocktailsDomainToUiMapper(TestResourceProvider(), BaseCocktailDomainToUiMapper())
        )
        assertEquals(expected, actual)

        failCocktailsDomain = CocktailsDomain.Fail(ErrorType.SERVICE_UNAVAILABLE)
        expected = CocktailsUi.Fail(SERVICE_UNAVAILABLE_MESSAGE)
        actual = failCocktailsDomain.map(
            BaseCocktailsDomainToUiMapper(TestResourceProvider(), BaseCocktailDomainToUiMapper())
        )
        assertEquals(expected, actual)
    }

    class TestResourceProvider : ResourceProvider {
        override fun getString(id: Int) = when (id) {
            R.string.no_connection_message -> NO_CONNECTION_MESSAGE
            R.string.service_unavailable_message -> SERVICE_UNAVAILABLE_MESSAGE
            else -> SOMETHING_WENT_WRONG
        }
    }

    private companion object {
        const val NO_CONNECTION_MESSAGE = "No connection. Please try again!"
        const val SERVICE_UNAVAILABLE_MESSAGE = "Service unavailable. Please try again!"
        const val SOMETHING_WENT_WRONG = "Something went wrong. Please try again!"
    }
}