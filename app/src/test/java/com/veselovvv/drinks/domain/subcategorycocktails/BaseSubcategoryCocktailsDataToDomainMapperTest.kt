package com.veselovvv.drinks.domain.subcategorycocktails

import com.veselovvv.drinks.core.ErrorType
import com.veselovvv.drinks.data.subcategorycocktails.SubcategoryCocktailData
import org.junit.Assert.assertEquals
import org.junit.Test
import java.net.UnknownHostException

class BaseSubcategoryCocktailsDataToDomainMapperTest {
    private val subcategoryCocktailDataToDomainMapper = BaseSubcategoryCocktailDataToDomainMapper()
    private val mapper = BaseSubcategoryCocktailsDataToDomainMapper(subcategoryCocktailDataToDomainMapper)

    @Test
    fun test_success() {
        val cocktails = listOf(
            SubcategoryCocktailData("1", "Margarita", "https://somephotopath1"),
            SubcategoryCocktailData("12", "Martini", "https://somephotopath2")
        )
        val expected = SubcategoryCocktailsDomain.Success(cocktails, subcategoryCocktailDataToDomainMapper)
        val actual = mapper.map(cocktails)
        assertEquals(expected, actual)
    }

    @Test
    fun test_fail() {
        var expected = SubcategoryCocktailsDomain.Fail(ErrorType.NO_CONNECTION)
        var actual = mapper.map(UnknownHostException())
        assertEquals(expected, actual)

        expected = SubcategoryCocktailsDomain.Fail(ErrorType.GENERIC_ERROR)
        actual = mapper.map(Exception())
        assertEquals(expected, actual)
    }
}