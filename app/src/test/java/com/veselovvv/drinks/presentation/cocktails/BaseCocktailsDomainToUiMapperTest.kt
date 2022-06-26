package com.veselovvv.drinks.presentation.cocktails

import com.veselovvv.drinks.R
import com.veselovvv.drinks.core.ErrorType
import com.veselovvv.drinks.core.ResourceProvider
import com.veselovvv.drinks.domain.cocktails.CocktailDomain
import org.junit.Assert.assertEquals
import org.junit.Test

class BaseCocktailsDomainToUiMapperTest {
    private val cocktailDomainToUiMapper = BaseCocktailDomainToUiMapper()
    private val cocktailsDomainToUiMapper = BaseCocktailsDomainToUiMapper(
        TestResourceProvider(), cocktailDomainToUiMapper
    )

    @Test
    fun test_success() {
        val data = listOf(
            CocktailDomain("1", "Margarita", "Ordinal", "https://somephotopath1"),
            CocktailDomain("12", "Martini", "Ordinal", "https://somephotopath2")
        )
        val expected = CocktailsUi.Success(data, cocktailDomainToUiMapper)
        val actual = cocktailsDomainToUiMapper.map(data)
        assertEquals(expected, actual)
    }

    @Test
    fun test_fail() {
        var expected = CocktailsUi.Fail(NO_CONNECTION_MESSAGE)
        var actual = cocktailsDomainToUiMapper.map(ErrorType.NO_CONNECTION)
        assertEquals(expected, actual)

        expected = CocktailsUi.Fail(SERVICE_UNAVAILABLE_MESSAGE)
        actual = cocktailsDomainToUiMapper.map(ErrorType.SERVICE_UNAVAILABLE)
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