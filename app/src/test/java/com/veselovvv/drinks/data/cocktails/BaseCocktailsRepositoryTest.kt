package com.veselovvv.drinks.data.cocktails

import com.veselovvv.drinks.data.cocktails.cloud.CocktailCloud
import com.veselovvv.drinks.data.cocktails.cloud.CocktailsCloudDataSource
import com.veselovvv.drinks.data.cocktails.cloud.CocktailsCloudMapper
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

class BaseCocktailsRepositoryTest {
    @Test
    fun test_fetch_cocktails_success() = runBlocking {
        val resultData = listOf(
            CocktailData("1", "Margarita", "Ordinal", "https://somephotopath1"),
            CocktailData("12", "Martini", "Ordinal", "https://somephotopath2")
        )
        val repository = BaseCocktailsRepository(
            TestCocktailsCloudDataSource(true), CocktailsCloudMapper.Base(ToCocktailMapper.Base())
        )
        val expected = CocktailsData.Success(resultData)
        val actual = repository.fetchCocktails()
        assertEquals(expected, actual)
    }

    @Test
    fun test_fetch_cocktails_fail() = runBlocking {
        val repository = BaseCocktailsRepository(
            TestCocktailsCloudDataSource(false), CocktailsCloudMapper.Base(ToCocktailMapper.Base())
        )
        val expected = CocktailsData.Fail(TestException(""))
        val actual = repository.fetchCocktails()
        assertEquals(expected, actual)
    }

    @Test
    fun test_search_cocktails_success() = runBlocking {
        val resultData = listOf(
            CocktailData("12", "Martini", "Ordinal", "https://somephotopath2")
        )
        val repository = BaseCocktailsRepository(
            TestCocktailsCloudDataSource(true), CocktailsCloudMapper.Base(ToCocktailMapper.Base())
        )
        val expected = CocktailsData.Success(resultData)
        val actual = repository.searchCocktails("Mart")
        assertEquals(expected, actual)
    }

    @Test
    fun test_search_cocktails_fail() = runBlocking {
        val repository = BaseCocktailsRepository(
            TestCocktailsCloudDataSource(false), CocktailsCloudMapper.Base(ToCocktailMapper.Base())
        )
        val expected = CocktailsData.Fail(TestException(""))
        val actual = repository.searchCocktails("")
        assertEquals(expected, actual)
    }

    class TestCocktailsCloudDataSource(private val returnSuccess: Boolean) : CocktailsCloudDataSource {
        override suspend fun fetchCocktails() = if (returnSuccess) listOf(
            CocktailCloud("1", "Margarita", "Ordinal", "https://somephotopath1"),
            CocktailCloud("12", "Martini", "Ordinal", "https://somephotopath2")
        ) else throw TestException("")
    }

    data class TestException(private val text: String) : Exception()
}