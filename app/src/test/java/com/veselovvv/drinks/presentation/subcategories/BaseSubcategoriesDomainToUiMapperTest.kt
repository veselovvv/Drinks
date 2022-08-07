package com.veselovvv.drinks.presentation.subcategories

import com.veselovvv.drinks.TestResourceProvider
import com.veselovvv.drinks.core.ErrorType
import com.veselovvv.drinks.domain.subcategories.SubcategoryDomain
import org.junit.Assert.assertEquals
import org.junit.Test

class BaseSubcategoriesDomainToUiMapperTest {
    private val subcategoryDomainToUiMapper = BaseSubcategoryDomainToUiMapper()
    private val mapper = BaseSubcategoriesDomainToUiMapper(
        TestResourceProvider(), subcategoryDomainToUiMapper
    )

    @Test
    fun test_success() {
        val subcategories = listOf(
            SubcategoryDomain("Ordinary Drink"),
            SubcategoryDomain("Cocktail")
        )
        val expected = SubcategoriesUi.Success(subcategories, subcategoryDomainToUiMapper)
        val actual = mapper.map(subcategories)
        assertEquals(expected, actual)
    }

    @Test
    fun test_fail() {
        var expected = SubcategoriesUi.Fail(NO_CONNECTION_MESSAGE)
        var actual = mapper.map(ErrorType.NO_CONNECTION)
        assertEquals(expected, actual)

        expected = SubcategoriesUi.Fail(SERVICE_UNAVAILABLE_MESSAGE)
        actual = mapper.map(ErrorType.SERVICE_UNAVAILABLE)
        assertEquals(expected, actual)
    }

    companion object {
        private const val NO_CONNECTION_MESSAGE = "No connection. Please try again!"
        private const val SERVICE_UNAVAILABLE_MESSAGE = "Service unavailable. Please try again!"
    }
}