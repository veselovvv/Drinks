package com.veselovvv.drinks.domain.subcategories

import com.veselovvv.drinks.core.ErrorType
import com.veselovvv.drinks.data.subcategories.SubcategoryData
import org.junit.Assert.assertEquals
import org.junit.Test
import java.net.UnknownHostException

class BaseSubcategoriesDataToDomainMapperTest {
    private val subcategoryDataToDomainMapper = BaseSubcategoryDataToDomainMapper()
    private val mapper = BaseSubcategoriesDataToDomainMapper(subcategoryDataToDomainMapper)

    @Test
    fun test_success() {
        val subcategories = listOf(
            SubcategoryData("Ordinary Drink"),
            SubcategoryData("Cocktail")
        )
        val expected = SubcategoriesDomain.Success(subcategories, subcategoryDataToDomainMapper)
        val actual = mapper.map(subcategories)
        assertEquals(expected, actual)
    }

    @Test
    fun test_fail() {
        var expected = SubcategoriesDomain.Fail(ErrorType.NO_CONNECTION)
        var actual = mapper.map(UnknownHostException())
        assertEquals(expected, actual)

        expected = SubcategoriesDomain.Fail(ErrorType.GENERIC_ERROR)
        actual = mapper.map(Exception())
        assertEquals(expected, actual)
    }
}