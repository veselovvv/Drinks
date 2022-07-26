package com.veselovvv.drinks.domain.searchingredient

import com.veselovvv.drinks.core.ErrorType
import com.veselovvv.drinks.data.searchingredient.IngredientData
import org.junit.Assert.assertEquals
import org.junit.Test
import java.net.UnknownHostException

class BaseIngredientsDataToDomainMapperTest {
    private val ingredientDataToDomainMapper = BaseIngredientDataToDomainMapper()
    private val mapper = BaseIngredientsDataToDomainMapper(ingredientDataToDomainMapper)

    @Test
    fun test_success() {
        val ingredient = IngredientData(
            "Fresh Mint",
            "Lamiaceae or Labiatae is a family of flowering plants commonly known as the mint or deadnettle family.",
            "Garnish",
            "No",
            ""
        )
        val expected = IngredientsDomain.Success(ingredient, ingredientDataToDomainMapper)
        val actual = mapper.map(ingredient)
        assertEquals(expected, actual)
    }

    @Test
    fun test_fail() {
        var expected = IngredientsDomain.Fail(ErrorType.NO_CONNECTION)
        var actual = mapper.map(UnknownHostException())
        assertEquals(expected, actual)

        expected = IngredientsDomain.Fail(ErrorType.GENERIC_ERROR)
        actual = mapper.map(Exception())
        assertEquals(expected, actual)
    }

    @Test
    fun test_no_results() {
        val expected = IngredientsDomain.NoResults
        val actual = mapper.map()
        assertEquals(expected, actual)
    }
}