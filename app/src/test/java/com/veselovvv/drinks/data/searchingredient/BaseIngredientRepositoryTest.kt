package com.veselovvv.drinks.data.searchingredient

import com.veselovvv.drinks.data.TestException
import com.veselovvv.drinks.data.searchingredient.cloud.IngredientCloud
import com.veselovvv.drinks.data.searchingredient.cloud.IngredientCloudDataSource
import com.veselovvv.drinks.data.searchingredient.cloud.IngredientCloudMapper
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

class BaseIngredientRepositoryTest {
    @Test
    fun test_fetch_ingredient_success() = runBlocking {
        val testCloudDataSource = TestIngredientCloudDataSource(true)
        val repository = IngredientRepository.Base(
            testCloudDataSource, IngredientCloudMapper.Base(ToIngredientMapper.Base())
        )
        val expected = IngredientsData.Success(
            IngredientData(
                "Fresh Mint",
                "Lamiaceae or Labiatae is a family of flowering plants commonly known as the mint or deadnettle family.",
                "Garnish",
                "No",
                ""
            )
        )
        val actual = repository.fetchIngredient("mint")
        assertEquals(expected, actual)
    }

    @Test
    fun test_fetch_ingredient_fail() = runBlocking {
        val testCloudDataSource = TestIngredientCloudDataSource(false)
        val repository = IngredientRepository.Base(
            testCloudDataSource, IngredientCloudMapper.Base(ToIngredientMapper.Base())
        )
        val expected = IngredientsData.Fail(TestException(""))
        val actual = repository.fetchIngredient("")
        assertEquals(expected, actual)
    }

    class TestIngredientCloudDataSource(private val success: Boolean) : IngredientCloudDataSource {
        override suspend fun fetchIngredient(name: String) = if (success) IngredientCloud(
            "Fresh Mint",
            "Lamiaceae or Labiatae is a family of flowering plants commonly known as the mint or deadnettle family.",
            "Garnish",
            "No",
            null
        ) else throw TestException("")
    }
}