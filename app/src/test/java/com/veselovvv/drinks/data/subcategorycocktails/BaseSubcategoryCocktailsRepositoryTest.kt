package com.veselovvv.drinks.data.subcategorycocktails

import com.veselovvv.drinks.core.Category
import com.veselovvv.drinks.data.TestException
import com.veselovvv.drinks.data.subcategorycocktails.cloud.SubcategoryCocktailCloud
import com.veselovvv.drinks.data.subcategorycocktails.cloud.SubcategoryCocktailsCloudDataSource
import com.veselovvv.drinks.data.subcategorycocktails.cloud.SubcategoryCocktailsCloudMapper
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

class BaseSubcategoryCocktailsRepositoryTest {
    @Test
    fun test_fetch_cocktails_success() = runBlocking {
        val testCloudDataSource = TestSubcategoryCocktailsCloudDataSource(true)
        val repository = SubcategoryCocktailsRepository.Base(
            testCloudDataSource,
            SubcategoryCocktailsCloudMapper.Base(ToSubcategoryCocktailMapper.Base())
        )
        val expected = SubcategoryCocktailsData.Success(
            listOf(
                SubcategoryCocktailData("Margarita", "https://somephotopath1"),
                SubcategoryCocktailData("Martini", "https://somephotopath2")
            )
        )
        val actual = repository.fetchCocktails(Category.ALCOHOLIC,"Alcoholic")
        assertEquals(expected, actual)
    }

    @Test
    fun test_fetch_cocktails_fail() = runBlocking {
        val testCloudDataSource = TestSubcategoryCocktailsCloudDataSource(false)
        val repository = SubcategoryCocktailsRepository.Base(
            testCloudDataSource,
            SubcategoryCocktailsCloudMapper.Base(ToSubcategoryCocktailMapper.Base())
        )
        val expected = SubcategoryCocktailsData.Fail(TestException(""))
        val actual = repository.fetchCocktails(Category.ALCOHOLIC, "Alcoholic")
        assertEquals(expected, actual)
    }

    class TestSubcategoryCocktailsCloudDataSource(
        private val success: Boolean
    ) : SubcategoryCocktailsCloudDataSource {
        override suspend fun fetchCocktails(category: Category, subcategoryName: String) =
            if (success) listOf(
                SubcategoryCocktailCloud("Margarita", "https://somephotopath1"),
                SubcategoryCocktailCloud("Martini", "https://somephotopath2")
            ) else throw TestException("")
    }
}