package com.veselovvv.drinks.domain.cocktaildetails

import com.veselovvv.drinks.core.ErrorType
import com.veselovvv.drinks.data.cocktaildetails.CocktailDetailsData
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test
import java.net.UnknownHostException

class FetchCocktailDetailsUseCaseTest {
    @Test
    fun test_success() = runBlocking {
        val data = CocktailDetailsData(
            "Alcoholic",
            "Cocktail glass",
            "Rub the rim of the glass with the lime slice to make the salt stick to it.",
            listOf("Tequila", "Triple sec", "Lime juice", "Salt", "", "", "", "", "", "")
        )
        val cocktailDetailsDataToDomainMapper = BaseCocktailDetailsDataToDomainMapper()
        val fetchCocktailDetailsUseCase = FetchCocktailDetailsUseCase(
            TestCocktailsDetailsRepository(),
            BaseCocktailsDetailsDataToDomainMapper(cocktailDetailsDataToDomainMapper)
        )
        val expected = CocktailsDetailsDomain.Success(data, cocktailDetailsDataToDomainMapper)
        val actual = fetchCocktailDetailsUseCase.execute("")
        assertEquals(expected, actual)
    }

    @Test
    fun test_fail() = runBlocking {
        val cocktailDetailsDataToDomainMapper = BaseCocktailDetailsDataToDomainMapper()
        var fetchCocktailDetailsUseCase = FetchCocktailDetailsUseCase(
            TestCocktailsDetailsRepository(UnknownHostException()),
            BaseCocktailsDetailsDataToDomainMapper(cocktailDetailsDataToDomainMapper)
        )
        var expected = CocktailsDetailsDomain.Fail(ErrorType.NO_CONNECTION)
        var actual = fetchCocktailDetailsUseCase.execute("")
        assertEquals(expected, actual)

        fetchCocktailDetailsUseCase = FetchCocktailDetailsUseCase(
            TestCocktailsDetailsRepository(Exception()),
            BaseCocktailsDetailsDataToDomainMapper(cocktailDetailsDataToDomainMapper)
        )
        expected = CocktailsDetailsDomain.Fail(ErrorType.GENERIC_ERROR)
        actual = fetchCocktailDetailsUseCase.execute("")
        assertEquals(expected, actual)
    }
}