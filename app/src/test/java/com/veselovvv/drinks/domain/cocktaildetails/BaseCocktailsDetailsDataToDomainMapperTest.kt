package com.veselovvv.drinks.domain.cocktaildetails

import com.veselovvv.drinks.core.ErrorType
import com.veselovvv.drinks.data.cocktaildetails.CocktailDetailsData
import org.junit.Assert.assertEquals
import org.junit.Test
import java.net.UnknownHostException

class BaseCocktailsDetailsDataToDomainMapperTest {
    private val cocktailDetailsDataToDomainMapper = BaseCocktailDetailsDataToDomainMapper()
    private val cocktailsDetailsDataToDomainMapper =
        BaseCocktailsDetailsDataToDomainMapper(cocktailDetailsDataToDomainMapper)

    @Test
    fun test_success() {
        val data = CocktailDetailsData(
            "Alcoholic",
            "Cocktail glass",
            "Rub the rim of the glass with the lime slice to make the salt stick to it.",
            listOf("Tequila", "Triple sec", "Lime juice", "Salt", "", "", "", "", "", "")
        )
        val expected = CocktailsDetailsDomain.Success(data, cocktailDetailsDataToDomainMapper)
        val actual = cocktailsDetailsDataToDomainMapper.map(data)
        assertEquals(expected, actual)
    }

    @Test
    fun test_fail() {
        var expected = CocktailsDetailsDomain.Fail(ErrorType.NO_CONNECTION)
        var actual = cocktailsDetailsDataToDomainMapper.map(UnknownHostException())
        assertEquals(expected, actual)

        expected = CocktailsDetailsDomain.Fail(ErrorType.GENERIC_ERROR)
        actual = cocktailsDetailsDataToDomainMapper.map(Exception())
        assertEquals(expected, actual)
    }
}