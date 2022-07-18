package com.veselovvv.drinks.domain.searchingredient

import com.veselovvv.drinks.core.ErrorType
import com.veselovvv.drinks.data.searchingredient.IngredientData
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test
import java.net.UnknownHostException

class FetchIngredientUseCaseTest {
    @Test
    fun test_success() = runBlocking {
        val ingredient = IngredientData(
            "Fresh Mint",
            "Lamiaceae or Labiatae is a family of flowering plants commonly known as the mint or deadnettle family.",
            "Garnish",
            "No",
            ""
        )
        val ingredientDataToDomainMapper = BaseIngredientDataToDomainMapper()
        val useCase = FetchIngredientUseCase(
            TestIngredientRepository(),
            BaseIngredientsDataToDomainMapper(ingredientDataToDomainMapper)
        )
        val expected = IngredientsDomain.Success(ingredient, ingredientDataToDomainMapper)
        val actual = useCase.execute("mint")
        assertEquals(expected, actual)
    }

    @Test
    fun test_fail() = runBlocking {
        val ingredientDataToDomainMapper = BaseIngredientDataToDomainMapper()
        var useCase = FetchIngredientUseCase(
            TestIngredientRepository(UnknownHostException()),
            BaseIngredientsDataToDomainMapper(ingredientDataToDomainMapper)
        )
        var expected = IngredientsDomain.Fail(ErrorType.NO_CONNECTION)
        var actual = useCase.execute("")
        assertEquals(expected, actual)

        useCase = FetchIngredientUseCase(
            TestIngredientRepository(Exception()),
            BaseIngredientsDataToDomainMapper(ingredientDataToDomainMapper)
        )
        expected = IngredientsDomain.Fail(ErrorType.GENERIC_ERROR)
        actual = useCase.execute("")
        assertEquals(expected, actual)
    }
}