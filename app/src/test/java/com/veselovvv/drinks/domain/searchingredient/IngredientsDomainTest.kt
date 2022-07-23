package com.veselovvv.drinks.domain.searchingredient

import com.veselovvv.drinks.TestResourceProvider
import com.veselovvv.drinks.core.ErrorType
import com.veselovvv.drinks.data.searchingredient.IngredientData
import com.veselovvv.drinks.presentation.searchingredient.BaseIngredientDomainToUiMapper
import com.veselovvv.drinks.presentation.searchingredient.BaseIngredientsDomainToUiMapper
import com.veselovvv.drinks.presentation.searchingredient.IngredientsUi
import org.junit.Assert.assertEquals
import org.junit.Test

class IngredientsDomainTest {
    @Test
    fun test_success() {
        val ingredient = IngredientData(
            "Fresh Mint",
            "Lamiaceae or Labiatae is a family of flowering plants commonly known as the mint or deadnettle family.",
            "Garnish",
            "No",
            ""
        )
        val resultIngredient = IngredientDomain(
            "Fresh Mint",
            "Lamiaceae or Labiatae is a family of flowering plants commonly known as the mint or deadnettle family.",
            "Garnish",
            "No",
            ""
        )
        val ingredientDomainToUiMapper = BaseIngredientDomainToUiMapper()
        val domain = IngredientsDomain.Success(ingredient, BaseIngredientDataToDomainMapper())
        val expected = IngredientsUi.Success(resultIngredient, ingredientDomainToUiMapper)
        val actual = domain.map(
            BaseIngredientsDomainToUiMapper(TestResourceProvider(), ingredientDomainToUiMapper)
        )
        assertEquals(expected, actual)
    }

    @Test
    fun test_fail() {
        var domain = IngredientsDomain.Fail(ErrorType.NO_CONNECTION)
        var expected = IngredientsUi.Fail(NO_CONNECTION_MESSAGE)
        var actual = domain.map(
            BaseIngredientsDomainToUiMapper(TestResourceProvider(), BaseIngredientDomainToUiMapper())
        )
        assertEquals(expected, actual)

        domain = IngredientsDomain.Fail(ErrorType.SERVICE_UNAVAILABLE)
        expected = IngredientsUi.Fail(SERVICE_UNAVAILABLE_MESSAGE)
        actual = domain.map(
            BaseIngredientsDomainToUiMapper(TestResourceProvider(), BaseIngredientDomainToUiMapper())
        )
        assertEquals(expected, actual)
    }

    companion object {
        private const val NO_CONNECTION_MESSAGE = "No connection. Please try again!"
        private const val SERVICE_UNAVAILABLE_MESSAGE = "Service unavailable. Please try again!"
    }
}