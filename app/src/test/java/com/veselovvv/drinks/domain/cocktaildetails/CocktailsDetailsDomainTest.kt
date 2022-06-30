package com.veselovvv.drinks.domain.cocktaildetails

import com.veselovvv.drinks.R
import com.veselovvv.drinks.core.ErrorType
import com.veselovvv.drinks.core.ResourceProvider
import com.veselovvv.drinks.data.cocktaildetails.CocktailDetailsData
import com.veselovvv.drinks.presentation.cocktaildetails.BaseCocktailDetailsDomainToUiMapper
import com.veselovvv.drinks.presentation.cocktaildetails.BaseCocktailsDetailsDomainToUiMapper
import com.veselovvv.drinks.presentation.cocktaildetails.CocktailsDetailsUi
import org.junit.Assert.assertEquals
import org.junit.Test

class CocktailsDetailsDomainTest {
    @Test
    fun test_success() {
        val data = CocktailDetailsData(
            "Alcoholic",
            "Cocktail glass",
            "Rub the rim of the glass with the lime slice to make the salt stick to it.",
            listOf("Tequila", "Triple sec", "Lime juice", "Salt", "", "", "", "", "", "")
        )
        val resultData = CocktailDetailsDomain(
            "Alcoholic",
            "Cocktail glass",
            "Rub the rim of the glass with the lime slice to make the salt stick to it.",
            listOf("Tequila", "Triple sec", "Lime juice", "Salt", "", "", "", "", "", "")
        )
        val cocktailDetailsDomainToUiMapper = BaseCocktailDetailsDomainToUiMapper()
        val successCocktailsDetailsDomain =
            CocktailsDetailsDomain.Success(data, BaseCocktailDetailsDataToDomainMapper())
        val expected = CocktailsDetailsUi.Success(resultData, cocktailDetailsDomainToUiMapper)
        val actual = successCocktailsDetailsDomain.map(
            BaseCocktailsDetailsDomainToUiMapper(TestResourceProvider(), cocktailDetailsDomainToUiMapper)
        )
        assertEquals(expected, actual)
    }

    @Test
    fun test_fail() {
        var failCocktailsDetailsDomain = CocktailsDetailsDomain.Fail(ErrorType.NO_CONNECTION)
        var expected = CocktailsDetailsUi.Fail(NO_CONNECTION_MESSAGE)
        var actual = failCocktailsDetailsDomain.map(
            BaseCocktailsDetailsDomainToUiMapper(TestResourceProvider(), BaseCocktailDetailsDomainToUiMapper())
        )
        assertEquals(expected, actual)

        failCocktailsDetailsDomain = CocktailsDetailsDomain.Fail(ErrorType.SERVICE_UNAVAILABLE)
        expected = CocktailsDetailsUi.Fail(SERVICE_UNAVAILABLE_MESSAGE)
        actual = failCocktailsDetailsDomain.map(
            BaseCocktailsDetailsDomainToUiMapper(TestResourceProvider(), BaseCocktailDetailsDomainToUiMapper())
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

    companion object {
        private const val NO_CONNECTION_MESSAGE = "No connection. Please try again!"
        private const val SERVICE_UNAVAILABLE_MESSAGE = "Service unavailable. Please try again!"
        private const val SOMETHING_WENT_WRONG = "Something went wrong. Please try again!"
    }
}