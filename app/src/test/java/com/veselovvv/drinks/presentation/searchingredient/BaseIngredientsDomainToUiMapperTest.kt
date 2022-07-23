package com.veselovvv.drinks.presentation.searchingredient

import com.veselovvv.drinks.TestResourceProvider
import com.veselovvv.drinks.core.ErrorType
import com.veselovvv.drinks.domain.searchingredient.IngredientDomain
import org.junit.Assert.assertEquals
import org.junit.Test

class BaseIngredientsDomainToUiMapperTest {
    private val ingredientDomainToUiMapper = BaseIngredientDomainToUiMapper()
    private val mapper = BaseIngredientsDomainToUiMapper(
        TestResourceProvider(), ingredientDomainToUiMapper
    )

    @Test
    fun test_success() {
        val ingredient = IngredientDomain(
            "Fresh Mint",
            "Lamiaceae or Labiatae is a family of flowering plants commonly known as the mint or deadnettle family.",
            "Garnish",
            "No",
            ""
        )
        val expected = IngredientsUi.Success(ingredient, ingredientDomainToUiMapper)
        val actual = mapper.map(ingredient)
        assertEquals(expected, actual)
    }

    @Test
    fun test_fail() {
        var expected = IngredientsUi.Fail(NO_CONNECTION_MESSAGE)
        var actual = mapper.map(ErrorType.NO_CONNECTION)
        assertEquals(expected, actual)

        expected = IngredientsUi.Fail(SERVICE_UNAVAILABLE_MESSAGE)
        actual = mapper.map(ErrorType.SERVICE_UNAVAILABLE)
        assertEquals(expected, actual)
    }

    companion object {
        private const val NO_CONNECTION_MESSAGE = "No connection. Please try again!"
        private const val SERVICE_UNAVAILABLE_MESSAGE = "Service unavailable. Please try again!"
    }
}