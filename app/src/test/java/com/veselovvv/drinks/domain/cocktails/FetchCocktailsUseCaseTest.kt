package com.veselovvv.drinks.domain.cocktails

import com.veselovvv.drinks.core.ErrorType
import com.veselovvv.drinks.data.cocktails.CocktailData
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test
import java.net.UnknownHostException

class FetchCocktailsUseCaseTest {
    @Test
    fun test_success() = runBlocking {
        val data = listOf(
            CocktailData("1", "Margarita", "Ordinal", "https://somephotopath1"),
            CocktailData("12", "Martini", "Ordinal", "https://somephotopath2")
        )
        val cocktailDataToDomainMapper = BaseCocktailDataToDomainMapper()
        val fetchCocktailsUseCase = FetchCocktailsUseCase(
            TestCocktailsRepository(), BaseCocktailsDataToDomainMapper(cocktailDataToDomainMapper)
        )
        val expected = CocktailsDomain.Success(data, cocktailDataToDomainMapper)
        val actual = fetchCocktailsUseCase.execute()
        assertEquals(expected, actual)
    }

    @Test
    fun test_fail() = runBlocking {
        var cocktailDataToDomainMapper = BaseCocktailDataToDomainMapper()
        var fetchCocktailsUseCase = FetchCocktailsUseCase(
            TestCocktailsRepository(UnknownHostException()), BaseCocktailsDataToDomainMapper(cocktailDataToDomainMapper)
        )
        var expected = CocktailsDomain.Fail(ErrorType.NO_CONNECTION)
        var actual = fetchCocktailsUseCase.execute()
        assertEquals(expected, actual)

        cocktailDataToDomainMapper = BaseCocktailDataToDomainMapper()
        fetchCocktailsUseCase = FetchCocktailsUseCase(
            TestCocktailsRepository(Exception()), BaseCocktailsDataToDomainMapper(cocktailDataToDomainMapper)
        )
        expected = CocktailsDomain.Fail(ErrorType.GENERIC_ERROR)
        actual = fetchCocktailsUseCase.execute()
        assertEquals(expected, actual)
    }
}