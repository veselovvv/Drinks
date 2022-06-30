package com.veselovvv.drinks.domain.cocktails

import com.veselovvv.drinks.core.ErrorType
import com.veselovvv.drinks.data.cocktails.CocktailData
import org.junit.Assert.assertEquals
import org.junit.Test
import java.net.UnknownHostException

class BaseCocktailsDataToDomainMapperTest {
    private val cocktailDataToDomainMapper = BaseCocktailDataToDomainMapper()
    private val cocktailsDataToDomainMapper = BaseCocktailsDataToDomainMapper(cocktailDataToDomainMapper)

    @Test
    fun test_success() {
        val data = listOf(
            CocktailData("1", "Margarita", "Ordinal", "https://somephotopath1"),
            CocktailData("12", "Martini", "Ordinal", "https://somephotopath2")
        )
        val expected = CocktailsDomain.Success(data, cocktailDataToDomainMapper)
        val actual = cocktailsDataToDomainMapper.map(data)
        assertEquals(expected, actual)
    }

    @Test
    fun test_fail() {
        var expected = CocktailsDomain.Fail(ErrorType.NO_CONNECTION)
        var actual = cocktailsDataToDomainMapper.map(UnknownHostException())
        assertEquals(expected, actual)

        expected = CocktailsDomain.Fail(ErrorType.GENERIC_ERROR)
        actual = cocktailsDataToDomainMapper.map(Exception())
        assertEquals(expected, actual)
    }
}