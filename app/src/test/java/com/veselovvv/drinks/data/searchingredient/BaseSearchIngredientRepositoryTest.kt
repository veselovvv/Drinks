package com.veselovvv.drinks.data.searchingredient

import com.veselovvv.drinks.data.TestException
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

class BaseSearchIngredientRepositoryTest {
    @Test
    fun test_fetch_ingredient_success() = runBlocking {
        val testCloudDataSource = TestSearchIngredientCloudDataSource(true)
        val repository = SearchIngredientRepository.Base(
            testCloudDataSource,
            SearchIngredientCloudMapper.Base(ToSearchIngredientMapper.Base())
        )
        val expected = SearchIngredientsData.Success(
            SearchIngredientData(
                "Fresh Mint",
                "Lamiaceae or Labiatae is a family of flowering plants commonly known as the mint or deadnettle family.",
                "Garnish",
                "No",
                ""
            )
        )
        val actual = repository.fetchIngredient("fresh")
        assertEquals(expected, actual)
    }

    @Test
    fun test_fetch_ingredient_fail() = runBlocking {
        val testCloudDataSource = TestSearchIngredientCloudDataSource(false)
        val repository = SearchIngredientRepository.Base(
            testCloudDataSource,
            SearchIngredientCloudMapper.Base(ToSearchIngredientMapper.Base())
        )
        val expected = SearchIngredientsData.Fail(TestException(""))
        val actual = repository.fetchIngredient("")
        assertEquals(expected, actual)
    }

    class TestSearchIngredientCloudDataSource(
        private val success: Boolean
    ) : SearchIngredientCloudDataSource {
        override suspend fun fetchIngredient(name: String) = if (success) SearchIngredientCloud(
            "Fresh Mint",
            "Lamiaceae or Labiatae is a family of flowering plants commonly known as the mint or deadnettle family.",
            "Garnish",
            "No",
            null
        ) else throw TestException("")
    }
}