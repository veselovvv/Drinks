package com.veselovvv.drinks.data.cocktails

import com.veselovvv.drinks.data.TestException
import com.veselovvv.drinks.data.cocktails.cache.CocktailDb
import com.veselovvv.drinks.data.cocktails.cache.CocktailsCacheDataSource
import com.veselovvv.drinks.data.cocktails.cache.CocktailsCacheMapper
import com.veselovvv.drinks.data.cocktails.cloud.CocktailCloud
import com.veselovvv.drinks.data.cocktails.cloud.CocktailsCloudDataSource
import com.veselovvv.drinks.data.cocktails.cloud.CocktailsCloudMapper
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

class BaseCocktailsRepositoryTest {
    @Test
    fun test_fetch_cocktails_cloud_fail_cache_fail() = runBlocking {
        val testCloudDataSource = TestCocktailsCloudDataSource(false)
        val testCacheDataSource = TestCocktailsCacheDataSource(false)
        val repository = CocktailsRepository.Base(
            testCloudDataSource,
            testCacheDataSource,
            CocktailsCloudMapper.Base(ToCocktailMapper.Base()),
            CocktailsCacheMapper.Base(ToCocktailMapper.Base())
        )
        val expected = CocktailsData.Fail(TestException(""))
        val actual = repository.fetchCocktails()
        assertEquals(expected, actual)
    }

    @Test
    fun test_fetch_cocktails_cloud_success_cache_fail() = runBlocking {
        val testCloudDataSource = TestCocktailsCloudDataSource(true)
        val testCacheDataSource = TestCocktailsCacheDataSource(false)
        val repository = CocktailsRepository.Base(
            testCloudDataSource,
            testCacheDataSource,
            CocktailsCloudMapper.Base(ToCocktailMapper.Base()),
            CocktailsCacheMapper.Base(ToCocktailMapper.Base())
        )
        val expected = CocktailsData.Success(
            listOf(
                CocktailData("1", "Margarita", "Ordinal", "https://somephotopath1"),
                CocktailData("12", "Martini", "Ordinal", "https://somephotopath2")
            )
        )
        val actual = repository.fetchCocktails()
        assertEquals(expected, actual)
    }

    @Test
    fun test_fetch_cocktails_cloud_fail_cache_success() = runBlocking {
        val testCloudDataSource = TestCocktailsCloudDataSource(false)
        val testCacheDataSource = TestCocktailsCacheDataSource(true)
        val repository = CocktailsRepository.Base(
            testCloudDataSource,
            testCacheDataSource,
            CocktailsCloudMapper.Base(ToCocktailMapper.Base()),
            CocktailsCacheMapper.Base(ToCocktailMapper.Base())
        )
        val expected = CocktailsData.Success(
            listOf(
                CocktailData("1", "Margarita", "Ordinal", "https://somephotopath1"),
                CocktailData("12", "Martini", "Ordinal", "https://somephotopath2")
            )
        )
        val actual = repository.fetchCocktails()
        assertEquals(expected, actual)
    }

    @Test
    fun test_fetch_cocktails_cloud_success_cache_success() = runBlocking {
        val testCloudDataSource = TestCocktailsCloudDataSource(true)
        val testCacheDataSource = TestCocktailsCacheDataSource(true)
        val repository = CocktailsRepository.Base(
            testCloudDataSource,
            testCacheDataSource,
            CocktailsCloudMapper.Base(ToCocktailMapper.Base()),
            CocktailsCacheMapper.Base(ToCocktailMapper.Base())
        )
        val expected = CocktailsData.Success(
            listOf(
                CocktailData("1", "Margarita", "Ordinal", "https://somephotopath1"),
                CocktailData("12", "Martini", "Ordinal", "https://somephotopath2")
            )
        )
        val actual = repository.fetchCocktails()
        assertEquals(expected, actual)
    }

    @Test
    fun test_fetch_cocktails_from_network_success() = runBlocking {
        val testCloudDataSource = TestCocktailsCloudDataSource(true)
        val testCacheDataSource = TestCocktailsCacheDataSource(false)
        val repository = CocktailsRepository.Base(
            testCloudDataSource,
            testCacheDataSource,
            CocktailsCloudMapper.Base(ToCocktailMapper.Base()),
            CocktailsCacheMapper.Base(ToCocktailMapper.Base())
        )
        val expected = CocktailsData.Success(
            listOf(
                CocktailData("1", "Margarita", "Ordinal", "https://somephotopath1"),
                CocktailData("12", "Martini", "Ordinal", "https://somephotopath2")
            )
        )
        val actual = repository.fetchCocktailsFromNetwork()
        assertEquals(expected, actual)
    }

    @Test
    fun test_fetch_cocktails_from_network_fail() = runBlocking {
        val testCloudDataSource = TestCocktailsCloudDataSource(false)
        val testCacheDataSource = TestCocktailsCacheDataSource(false)
        val repository = CocktailsRepository.Base(
            testCloudDataSource,
            testCacheDataSource,
            CocktailsCloudMapper.Base(ToCocktailMapper.Base()),
            CocktailsCacheMapper.Base(ToCocktailMapper.Base())
        )
        val expected = CocktailsData.Fail(TestException(""))
        val actual = repository.fetchCocktailsFromNetwork()
        assertEquals(expected, actual)
    }

    @Test
    fun test_search_cocktails_success() = runBlocking {
        val testCloudDataSource = TestCocktailsCloudDataSource(false)
        val testCacheDataSource = TestCocktailsCacheDataSource(true)
        val repository = CocktailsRepository.Base(
            testCloudDataSource,
            testCacheDataSource,
            CocktailsCloudMapper.Base(ToCocktailMapper.Base()),
            CocktailsCacheMapper.Base(ToCocktailMapper.Base())
        )
        val expected = CocktailsData.Success(
            listOf(
                CocktailData("12", "Martini", "Ordinal", "https://somephotopath2")
            )
        )
        val actual = repository.searchCocktails("Mart")
        assertEquals(expected, actual)
    }

    @Test
    fun test_search_cocktails_fail() = runBlocking {
        val testCloudDataSource = TestCocktailsCloudDataSource(false)
        val testCacheDataSource = TestCocktailsCacheDataSource(false)
        val repository = CocktailsRepository.Base(
            testCloudDataSource,
            testCacheDataSource,
            CocktailsCloudMapper.Base(ToCocktailMapper.Base()),
            CocktailsCacheMapper.Base(ToCocktailMapper.Base())
        )
        val expected = CocktailsData.Success(listOf())
        val actual = repository.searchCocktails("")
        assertEquals(expected, actual)
    }

    class TestCocktailsCloudDataSource(private val success: Boolean) : CocktailsCloudDataSource {
        override suspend fun fetchCocktails() = if (success) listOf(
            CocktailCloud("1", "Margarita", "Ordinal", "https://somephotopath1"),
            CocktailCloud("12", "Martini", "Ordinal", "https://somephotopath2")
        ) else throw TestException("")
    }

    class TestCocktailsCacheDataSource(private val success: Boolean) : CocktailsCacheDataSource {
        override fun save(cocktails: List<CocktailData>) = Unit
        override fun clear() = Unit

        override fun read() = if (success) listOf(
            CocktailDb("1", "Margarita", "Ordinal", "https://somephotopath1"),
            CocktailDb("12", "Martini", "Ordinal", "https://somephotopath2")
        ) else listOf()
    }
}