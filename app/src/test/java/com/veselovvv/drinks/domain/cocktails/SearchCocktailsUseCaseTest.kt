package com.veselovvv.drinks.domain.cocktails

import com.veselovvv.drinks.core.ErrorType
import com.veselovvv.drinks.data.cocktails.CocktailData
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Test
import java.net.UnknownHostException

class SearchCocktailsUseCaseTest {
    @Test
    fun test_success() = runBlocking {
        val foundData = listOf(
            CocktailData("12", "Martini", "Ordinal", "https://somephotopath2")
        )
        val cocktailDataToDomainMapper = BaseCocktailDataToDomainMapper()
        val searchCocktailsUseCase = SearchCocktailsUseCase(
            TestCocktailsRepository(), BaseCocktailsDataToDomainMapper(cocktailDataToDomainMapper)
        )
        val expected = CocktailsDomain.Success(foundData, cocktailDataToDomainMapper)
        val actual = searchCocktailsUseCase.execute("")
        assertEquals(expected, actual)
    }

    @Test
    fun test_fail() = runBlocking {
        var cocktailDataToDomainMapper = BaseCocktailDataToDomainMapper()
        var searchCocktailsUseCase = SearchCocktailsUseCase(
            TestCocktailsRepository(UnknownHostException()), BaseCocktailsDataToDomainMapper(cocktailDataToDomainMapper)
        )
        var expected = CocktailsDomain.Fail(ErrorType.NO_CONNECTION)
        var actual = searchCocktailsUseCase.execute("")
        assertEquals(expected, actual)

        cocktailDataToDomainMapper = BaseCocktailDataToDomainMapper()
        searchCocktailsUseCase = SearchCocktailsUseCase(
            TestCocktailsRepository(Exception()), BaseCocktailsDataToDomainMapper(cocktailDataToDomainMapper)
        )
        expected = CocktailsDomain.Fail(ErrorType.GENERIC_ERROR)
        actual = searchCocktailsUseCase.execute("")
        assertEquals(expected, actual)
    }
}