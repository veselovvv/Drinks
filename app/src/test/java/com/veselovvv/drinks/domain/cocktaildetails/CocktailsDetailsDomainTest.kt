package com.veselovvv.drinks.domain.cocktaildetails

import com.veselovvv.drinks.TestResourceProvider
import com.veselovvv.drinks.core.ErrorType
import com.veselovvv.drinks.data.cocktaildetails.CocktailDetailsData
import com.veselovvv.drinks.presentation.cocktaildetails.BaseCocktailDetailsDomainToUiMapper
import com.veselovvv.drinks.presentation.cocktaildetails.BaseCocktailsDetailsDomainToUiMapper
import com.veselovvv.drinks.presentation.cocktaildetails.CocktailsDetailsUi
import org.junit.Assert.assertEquals
import org.junit.Test

class CocktailsDetailsDomainTest {
    @Test
    fun test_success() {
        val cocktailDetails = CocktailDetailsData(
            "Margarita",
            "Alcoholic",
            "Cocktail glass",
            "Rub the rim of the glass with the lime slice to make the salt stick to it.",
            listOf("Tequila", "Triple sec", "Lime juice", "Salt", "", "", "", "", "", "")
        )
        val resultCocktailDetails = CocktailDetailsDomain(
            "Alcoholic",
            "Cocktail glass",
            "Rub the rim of the glass with the lime slice to make the salt stick to it.",
            listOf("Tequila", "Triple sec", "Lime juice", "Salt", "", "", "", "", "", "")
        )
        val cocktailDetailsDomainToUiMapper = BaseCocktailDetailsDomainToUiMapper()
        val domain = CocktailsDetailsDomain.Success(
            cocktailDetails, BaseCocktailDetailsDataToDomainMapper()
        )
        val expected = CocktailsDetailsUi.Success(
            resultCocktailDetails, cocktailDetailsDomainToUiMapper
        )
        val actual = domain.map(
            BaseCocktailsDetailsDomainToUiMapper(
                TestResourceProvider(), cocktailDetailsDomainToUiMapper
            )
        )
        assertEquals(expected, actual)
    }

    @Test
    fun test_fail() {
        var domain = CocktailsDetailsDomain.Fail(ErrorType.NO_CONNECTION)
        var expected = CocktailsDetailsUi.Fail(NO_CONNECTION_MESSAGE)
        var actual = domain.map(
            BaseCocktailsDetailsDomainToUiMapper(
                TestResourceProvider(), BaseCocktailDetailsDomainToUiMapper()
            )
        )
        assertEquals(expected, actual)

        domain = CocktailsDetailsDomain.Fail(ErrorType.SERVICE_UNAVAILABLE)
        expected = CocktailsDetailsUi.Fail(SERVICE_UNAVAILABLE_MESSAGE)
        actual = domain.map(
            BaseCocktailsDetailsDomainToUiMapper(
                TestResourceProvider(), BaseCocktailDetailsDomainToUiMapper()
            )
        )
        assertEquals(expected, actual)
    }

    companion object {
        private const val NO_CONNECTION_MESSAGE = "No connection. Please try again!"
        private const val SERVICE_UNAVAILABLE_MESSAGE = "Service unavailable. Please try again!"
    }
}