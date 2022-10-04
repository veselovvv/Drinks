package com.veselovvv.drinks.data.subcategories

import com.veselovvv.drinks.core.Category
import com.veselovvv.drinks.data.TestException
import com.veselovvv.drinks.data.subcategories.cloud.*
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

class BaseSubcategoriesRepositoryTest {
    @Test
    fun test_fetch_subcategories_success() = runBlocking {
        val testCloudDataSource = TestSubcategoriesCloudDataSource(true)
        val repository = SubcategoriesRepository.Base(
            testCloudDataSource, SubcategoriesCloudMapper.Base(ToSubcategoryMapper.Base())
        )
        var expected = SubcategoriesData.Success(
            listOf(
                SubcategoryData("Ordinary Drink"),
                SubcategoryData("Cocktail")
            )
        )
        var actual = repository.fetchSubcategories(Category.CATEGORIES)
        assertEquals(expected, actual)

        expected = SubcategoriesData.Success(
            listOf(
                SubcategoryData("Highball glass"),
                SubcategoryData("Cocktail glass")
            )
        )
        actual = repository.fetchSubcategories(Category.GLASS)
        assertEquals(expected, actual)

        expected = SubcategoriesData.Success(
            listOf(
                SubcategoryData("Light rum"),
                SubcategoryData("Applejack")
            )
        )
        actual = repository.fetchSubcategories(Category.INGREDIENTS)
        assertEquals(expected, actual)

        expected = SubcategoriesData.Success(
            listOf(
                SubcategoryData("Alcoholic"),
                SubcategoryData("Non alcoholic")
            )
        )
        actual = repository.fetchSubcategories(Category.ALCOHOLIC)
        assertEquals(expected, actual)
    }

    @Test
    fun test_fetch_subcategories_fail() = runBlocking {
        val testCloudDataSource = TestSubcategoriesCloudDataSource(false)
        val repository = SubcategoriesRepository.Base(
            testCloudDataSource, SubcategoriesCloudMapper.Base(ToSubcategoryMapper.Base())
        )
        val expected = SubcategoriesData.Fail(TestException(""))
        val actual = repository.fetchSubcategories(Category.CATEGORIES)
        assertEquals(expected, actual)
    }

    class TestSubcategoriesCloudDataSource(private val success: Boolean) : SubcategoriesCloudDataSource {
        override suspend fun fetchCategorySubcategories() = if (success) listOf(
            CategorySubcategoryCloud("Ordinary Drink"),
            CategorySubcategoryCloud("Cocktail")
        ) else throw TestException("")

        override suspend fun fetchGlassSubcategories() = if (success) listOf(
            GlassSubcategoryCloud("Highball glass"),
            GlassSubcategoryCloud("Cocktail glass")
        ) else throw TestException("")

        override suspend fun fetchIngredientSubcategories() = if (success) listOf(
            IngredientSubcategoryCloud("Light rum"),
            IngredientSubcategoryCloud("Applejack")
        ) else throw TestException("")

        override suspend fun fetchAlcoholicSubcategories() = if (success) listOf(
            AlcoholicSubcategoryCloud("Alcoholic"),
            AlcoholicSubcategoryCloud("Non alcoholic")
        ) else throw TestException("")
    }
}