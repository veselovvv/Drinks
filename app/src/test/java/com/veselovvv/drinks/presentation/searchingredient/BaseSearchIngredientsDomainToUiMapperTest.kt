package com.veselovvv.drinks.presentation.searchingredient

import com.veselovvv.drinks.TestResourceProvider
import com.veselovvv.drinks.core.ErrorType
import org.junit.Assert.assertEquals
import org.junit.Test

class BaseSearchIngredientsDomainToUiMapperTest {
    private val searchIngredientDomainToUiMapper = BaseSearchIngredientDomainToUiMapper()
    private val mapper = BaseSearchIngredientsDomainToUiMapper(
        TestResourceProvider(), searchIngredientDomainToUiMapper
    )

    @Test
    fun test_success() {
        val searchIngredient = SearchIngredientDomain(
            "Fresh Mint",
            "Lamiaceae or Labiatae is a family of flowering plants commonly known as the mint or deadnettle family.",
            "Garnish",
            "No",
            ""
        )
        val expected = SearchIngredientsUi.Success(searchIngredient, searchIngredientDomainToUiMapper)
        val actual = mapper.map(searchIngredient)
        assertEquals(expected, actual)
    }

    @Test
    fun test_fail() {
        var expected = SearchIngredientsUi.Fail(NO_CONNECTION_MESSAGE)
        var actual = mapper.map(ErrorType.NO_CONNECTION)
        assertEquals(expected, actual)

        expected = SearchIngredientsUi.Fail(SERVICE_UNAVAILABLE_MESSAGE)
        actual = mapper.map(ErrorType.SERVICE_UNAVAILABLE)
        assertEquals(expected, actual)
    }

    companion object {
        private const val NO_CONNECTION_MESSAGE = "No connection. Please try again!"
        private const val SERVICE_UNAVAILABLE_MESSAGE = "Service unavailable. Please try again!"
    }
}