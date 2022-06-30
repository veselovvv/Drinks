package com.veselovvv.drinks.data.cocktaildetails

import com.veselovvv.drinks.data.cocktaildetails.cloud.CocktailDetailsCloud
import com.veselovvv.drinks.data.cocktaildetails.cloud.CocktailDetailsCloudDataSource
import com.veselovvv.drinks.data.cocktaildetails.cloud.CocktailDetailsCloudMapper
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

class BaseCocktailDetailsRepositoryTest {
    @Test
    fun test_fetch_cocktail_details_success() = runBlocking {
        val resultData = CocktailDetailsData(
            "Alcoholic",
            "Cocktail glass",
            "Rub the rim of the glass with the lime slice to make the salt stick to it.",
            listOf("Tequila", "Triple sec", "Lime juice", "Salt", "", "", "", "", "", "")
        )
        val repository = CocktailDetailsRepository.Base(
            TestCocktailDetailsCloudDataSource(true),
            CocktailDetailsCloudMapper.Base(ToCocktailDetailsMapper.Base())
        )
        val expected = CocktailsDetailsData.Success(resultData)
        val actual = repository.fetchCocktailDetails("")
        assertEquals(expected, actual)
    }

    @Test
    fun test_fetch_cocktail_details_fail() = runBlocking {
        val repository = CocktailDetailsRepository.Base(
            TestCocktailDetailsCloudDataSource(false),
            CocktailDetailsCloudMapper.Base(ToCocktailDetailsMapper.Base())
        )
        val expected = CocktailsDetailsData.Fail(TestException(""))
        val actual = repository.fetchCocktailDetails("")
        assertEquals(expected, actual)
    }

    class TestCocktailDetailsCloudDataSource(private val success: Boolean) : CocktailDetailsCloudDataSource {
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

    data class TestException(private val text: String) : Exception()
}