package com.veselovvv.drinks.data.randomcocktail

import com.veselovvv.drinks.data.TestException
import com.veselovvv.drinks.data.randomcocktail.cloud.RandomCocktailCloud
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

class BaseRandomCocktailRepositoryTest {
    @Test
    fun test_fetch_random_cocktail_success() = runBlocking {
        val testCloudDataSource = TestRandomCocktailCloudDataSource(true)
        val repository = RandomCocktailRepository.Base(
            testCloudDataSource,
            RandomCocktailCloudMapper.Base(ToRandomCocktailMapper.Base())
        )
        val expected = RandomCocktailsData.Success(
            RandomCocktailData(
                "Margarita",
                "Cocktail",
                "Alcoholic",
                "Cocktail glass",
                "Rub the rim of the glass with the lime slice to make the salt stick to it.",
                "https://somephotopath1",
                listOf("Tequila", "Triple sec", "Lime juice", "Salt", "", "", "", "", "", "")
            )
        )
        val actual = repository.fetchRandomCocktail()
        assertEquals(expected, actual)
    }

    @Test
    fun test_fetch_random_cocktail_fail() = runBlocking {
        val testCloudDataSource = TestRandomCocktailCloudDataSource(false)
        val repository = RandomCocktailRepository.Base(
            testCloudDataSource,
            RandomCocktailCloudMapper.Base(ToRandomCocktailMapper.Base())
        )
        val expected = RandomCocktailsData.Fail(TestException(""))
        val actual = repository.fetchRandomCocktail()
        assertEquals(expected, actual)
    }

    class TestRandomCocktailCloudDataSource(
        private val success: Boolean
    ) : RandomCocktailCloudDataSource {
        override suspend fun fetchRandomCocktail() = if (success) RandomCocktailCloud(
            "Margarita",
            "Cocktail",
            "Alcoholic",
            "Cocktail glass",
            "Rub the rim of the glass with the lime slice to make the salt stick to it.",
            "https://somephotopath1",
            "Tequila", "Triple sec", "Lime juice", "Salt",
            null, null, null, null, null, null
        ) else throw TestException("")
    }
}