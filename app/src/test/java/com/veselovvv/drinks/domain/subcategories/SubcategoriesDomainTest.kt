package com.veselovvv.drinks.domain.subcategories

import com.veselovvv.drinks.TestResourceProvider
import com.veselovvv.drinks.core.ErrorType
import com.veselovvv.drinks.data.subcategories.SubcategoryData
import com.veselovvv.drinks.presentation.subcategories.BaseSubcategoriesDomainToUiMapper
import com.veselovvv.drinks.presentation.subcategories.BaseSubcategoryDomainToUiMapper
import com.veselovvv.drinks.presentation.subcategories.SubcategoriesUi
import org.junit.Assert.assertEquals
import org.junit.Test

class SubcategoriesDomainTest {
    @Test
    fun test_success() {
        val subcategories = listOf(
            SubcategoryData("Ordinary Drink"),
            SubcategoryData("Cocktail")
        )
        val resultSubcategories = listOf(
            SubcategoryDomain("Ordinary Drink"),
            SubcategoryDomain("Cocktail")
        )
        val subcategoryDomainToUiMapper = BaseSubcategoryDomainToUiMapper()
        val domain = SubcategoriesDomain.Success(subcategories, BaseSubcategoryDataToDomainMapper())
        val expected = SubcategoriesUi.Success(resultSubcategories, subcategoryDomainToUiMapper)
        val actual = domain.map(
            BaseSubcategoriesDomainToUiMapper(TestResourceProvider(), subcategoryDomainToUiMapper)
        )
        assertEquals(expected, actual)
    }

    @Test
    fun test_fail() {
        var domain = SubcategoriesDomain.Fail(ErrorType.NO_CONNECTION)
        var expected = SubcategoriesUi.Fail(NO_CONNECTION_MESSAGE)
        var actual = domain.map(
            BaseSubcategoriesDomainToUiMapper(TestResourceProvider(), BaseSubcategoryDomainToUiMapper())
        )
        assertEquals(expected, actual)

        domain = SubcategoriesDomain.Fail(ErrorType.SERVICE_UNAVAILABLE)
        expected = SubcategoriesUi.Fail(SERVICE_UNAVAILABLE_MESSAGE)
        actual = domain.map(
            BaseSubcategoriesDomainToUiMapper(TestResourceProvider(), BaseSubcategoryDomainToUiMapper())
        )
        assertEquals(expected, actual)
    }

    companion object {
        private const val NO_CONNECTION_MESSAGE = "No connection. Please try again!"
        private const val SERVICE_UNAVAILABLE_MESSAGE = "Service unavailable. Please try again!"
    }
}