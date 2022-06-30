package com.veselovvv.drinks.presentation.cocktaildetails

import com.veselovvv.drinks.R
import com.veselovvv.drinks.core.ErrorType
import com.veselovvv.drinks.core.ResourceProvider
import com.veselovvv.drinks.domain.cocktaildetails.CocktailDetailsDomain
import org.junit.Assert.assertEquals
import org.junit.Test

class BaseCocktailsDetailsDomainToUiMapperTest {
    private val cocktailDetailsDomainToUiMapper = BaseCocktailDetailsDomainToUiMapper()
    private val cocktailsDetailsDomainToUiMapper = BaseCocktailsDetailsDomainToUiMapper(
        TestResourceProvider(), cocktailDetailsDomainToUiMapper
    )

    @Test
    fun test_success() {
        val data = CocktailDetailsDomain(
            "Alcoholic",
            "Cocktail glass",
            "Rub the rim of the glass with the lime slice to make the salt stick to it.",
            listOf("Tequila", "Triple sec", "Lime juice", "Salt", "", "", "", "", "", "")
        )
        val expected = CocktailsDetailsUi.Success(data, cocktailDetailsDomainToUiMapper)
        val actual = cocktailsDetailsDomainToUiMapper.map(data)
        assertEquals(expected, actual)
    }

    @Test
    fun test_fail() {
        var expected = CocktailsDetailsUi.Fail(NO_CONNECTION_MESSAGE)
        var actual = cocktailsDetailsDomainToUiMapper.map(ErrorType.NO_CONNECTION)
        assertEquals(expected, actual)

        expected = CocktailsDetailsUi.Fail(SERVICE_UNAVAILABLE_MESSAGE)
        actual = cocktailsDetailsDomainToUiMapper.map(ErrorType.SERVICE_UNAVAILABLE)
        assertEquals(expected, actual)
    }

    class TestResourceProvider : ResourceProvider {
        override fun getString(id: Int) = when (id) {
            R.string.no_connection_message -> NO_CONNECTION_MESSAGE
            R.string.service_unavailable_message -> SERVICE_UNAVAILABLE_MESSAGE
            else -> SOMETHING_WENT_WRONG
        }
    }

    companion object {
        private const val NO_CONNECTION_MESSAGE = "No connection. Please try again!"
        private const val SERVICE_UNAVAILABLE_MESSAGE = "Service unavailable. Please try again!"
        private const val SOMETHING_WENT_WRONG = "Something went wrong. Please try again!"
    }
}