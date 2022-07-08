package com.veselovvv.drinks.data.cocktaildetails

import com.veselovvv.drinks.data.TestException
import com.veselovvv.drinks.data.cocktaildetails.cache.CocktailDetailsCacheDataSource
import com.veselovvv.drinks.data.cocktaildetails.cache.CocktailDetailsCacheMapper
import com.veselovvv.drinks.data.cocktaildetails.cache.CocktailDetailsDb
import com.veselovvv.drinks.data.cocktaildetails.cloud.CocktailDetailsCloud
import com.veselovvv.drinks.data.cocktaildetails.cloud.CocktailDetailsCloudDataSource
import com.veselovvv.drinks.data.cocktaildetails.cloud.CocktailDetailsCloudMapper
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

class BaseCocktailDetailsRepositoryTest {
    @Test
    fun test_fetch_details_cloud_fail_cache_fail() = runBlocking {
        val testCloudDataSource = TestCocktailDetailsCloudDataSource(false)
        val testCacheDataSource = TestCocktailDetailsCacheDataSource(false)
        val repository = CocktailDetailsRepository.Base(
            testCloudDataSource,
            testCacheDataSource,
            CocktailDetailsCloudMapper.Base(ToCocktailDetailsMapper.Base()),
            CocktailDetailsCacheMapper.Base(ToCocktailDetailsMapper.Base())
        )
        val expected = CocktailsDetailsData.Fail(TestException(""))
        val actual = repository.fetchCocktailDetails("Margarita")
        assertEquals(expected, actual)
    }

    @Test
    fun test_fetch_details_cloud_success_cache_fail() = runBlocking {
        val testCloudDataSource = TestCocktailDetailsCloudDataSource(true)
        val testCacheDataSource = TestCocktailDetailsCacheDataSource(false)
        val repository = CocktailDetailsRepository.Base(
            testCloudDataSource,
            testCacheDataSource,
            CocktailDetailsCloudMapper.Base(ToCocktailDetailsMapper.Base()),
            CocktailDetailsCacheMapper.Base(ToCocktailDetailsMapper.Base())
        )
        val expected = CocktailsDetailsData.Success(
            CocktailDetailsData(
                "Margarita",
                "Alcoholic",
                "Cocktail glass",
                "Rub the rim of the glass with the lime slice to make the salt stick to it.",
                listOf("Tequila", "Triple sec", "Lime juice", "Salt", "", "", "", "", "", "")
            )
        )
        val actual = repository.fetchCocktailDetails("Margarita")
        assertEquals(expected, actual)
    }

    @Test
    fun test_fetch_details_cloud_fail_cache_success() = runBlocking {
        val testCloudDataSource = TestCocktailDetailsCloudDataSource(false)
        val testCacheDataSource = TestCocktailDetailsCacheDataSource(true)
        val repository = CocktailDetailsRepository.Base(
            testCloudDataSource,
            testCacheDataSource,
            CocktailDetailsCloudMapper.Base(ToCocktailDetailsMapper.Base()),
            CocktailDetailsCacheMapper.Base(ToCocktailDetailsMapper.Base())
        )
        val expected = CocktailsDetailsData.Success(
            CocktailDetailsData(
                "Margarita",
                "Alcoholic",
                "Cocktail glass",
                "Rub the rim of the glass with the lime slice to make the salt stick to it.",
                listOf("Tequila", "Triple sec", "Lime juice", "Salt", "", "", "", "", "", "")
            )
        )
        val actual = repository.fetchCocktailDetails("Margarita")
        assertEquals(expected, actual)
    }

    @Test
    fun test_fetch_details_cloud_success_cache_success() = runBlocking {
        val testCloudDataSource = TestCocktailDetailsCloudDataSource(true)
        val testCacheDataSource = TestCocktailDetailsCacheDataSource(true)
        val repository = CocktailDetailsRepository.Base(
            testCloudDataSource,
            testCacheDataSource,
            CocktailDetailsCloudMapper.Base(ToCocktailDetailsMapper.Base()),
            CocktailDetailsCacheMapper.Base(ToCocktailDetailsMapper.Base())
        )
        val expected = CocktailsDetailsData.Success(
            CocktailDetailsData(
                "Margarita",
                "Alcoholic",
                "Cocktail glass",
                "Rub the rim of the glass with the lime slice to make the salt stick to it.",
                listOf("Tequila", "Triple sec", "Lime juice", "Salt", "", "", "", "", "", "")
            )
        )
        val actual = repository.fetchCocktailDetails("Margarita")
        assertEquals(expected, actual)
    }

    @Test
    fun test_fetch_details_from_network_success() = runBlocking {
        val testCloudDataSource = TestCocktailDetailsCloudDataSource(true)
        val testCacheDataSource = TestCocktailDetailsCacheDataSource(false)
        val repository = CocktailDetailsRepository.Base(
            testCloudDataSource,
            testCacheDataSource,
            CocktailDetailsCloudMapper.Base(ToCocktailDetailsMapper.Base()),
            CocktailDetailsCacheMapper.Base(ToCocktailDetailsMapper.Base())
        )
        val expected = CocktailsDetailsData.Success(
            CocktailDetailsData(
                "Margarita",
                "Alcoholic",
                "Cocktail glass",
                "Rub the rim of the glass with the lime slice to make the salt stick to it.",
                listOf("Tequila", "Triple sec", "Lime juice", "Salt", "", "", "", "", "", "")
            )
        )
        val actual = repository.fetchCocktailDetailsFromNetwork("Margarita")
        assertEquals(expected, actual)
    }

    @Test
    fun test_fetch_details_from_network_fail() = runBlocking {
        val testCloudDataSource = TestCocktailDetailsCloudDataSource(false)
        val testCacheDataSource = TestCocktailDetailsCacheDataSource(false)
        val repository = CocktailDetailsRepository.Base(
            testCloudDataSource,
            testCacheDataSource,
            CocktailDetailsCloudMapper.Base(ToCocktailDetailsMapper.Base()),
            CocktailDetailsCacheMapper.Base(ToCocktailDetailsMapper.Base())
        )
        val expected = CocktailsDetailsData.Fail(TestException(""))
        val actual = repository.fetchCocktailDetailsFromNetwork("Margarita")
        assertEquals(expected, actual)
    }

    class TestCocktailDetailsCloudDataSource(
        private val success: Boolean
    ) : CocktailDetailsCloudDataSource {
        override suspend fun fetchCocktailDetails(cocktailName: String) =
            if (success) CocktailDetailsCloud(
                "Margarita",
                "Alcoholic",
                "Cocktail glass",
                "Rub the rim of the glass with the lime slice to make the salt stick to it.",
                "Tequila", "Triple sec", "Lime juice", "Salt",
                null, null, null, null, null, null)
            else throw TestException("")
    }

    class TestCocktailDetailsCacheDataSource(
        private val success: Boolean
    ) : CocktailDetailsCacheDataSource {
        override fun save(cocktailDetails: CocktailDetailsData) = Unit
        override fun clear(name: String) = Unit

        override fun read(name: String) = if (success) CocktailDetailsDb(
            "Margarita",
            "Alcoholic",
            "Cocktail glass",
            "Rub the rim of the glass with the lime slice to make the salt stick to it.",
            "Tequila", "Triple sec", "Lime juice", "Salt",
            "", "", "", "", "", ""
        ) else null
    }
}