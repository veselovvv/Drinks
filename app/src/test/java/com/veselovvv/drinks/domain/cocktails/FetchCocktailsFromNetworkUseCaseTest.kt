package com.veselovvv.drinks.domain.cocktails

import com.veselovvv.drinks.core.ErrorType
import com.veselovvv.drinks.data.cocktails.CocktailData
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test
import java.net.UnknownHostException

class FetchCocktailsFromNetworkUseCaseTest {
    @Test
    fun test_success() = runBlocking {
        val cocktails = listOf(
            CocktailData("1", "Margarita", "Ordinal", "https://somephotopath1"),
            CocktailData("12", "Martini", "Ordinal", "https://somephotopath2")
        )
        val cocktailDataToDomainMapper = BaseCocktailDataToDomainMapper()
        val useCase = FetchCocktailsFromNetworkUseCase(
            TestCocktailsRepository(), BaseCocktailsDataToDomainMapper(cocktailDataToDomainMapper)
        )
        val expected = CocktailsDomain.Success(cocktails, cocktailDataToDomainMapper)
        val actual = useCase.execute()
        Assert.assertEquals(expected, actual)
    }

    @Test
    fun test_fail() = runBlocking {
        var cocktailDataToDomainMapper = BaseCocktailDataToDomainMapper()
        var useCase = FetchCocktailsFromNetworkUseCase(
            TestCocktailsRepository(UnknownHostException()), BaseCocktailsDataToDomainMapper(cocktailDataToDomainMapper)
        )
        var expected = CocktailsDomain.Fail(ErrorType.NO_CONNECTION)
        var actual = useCase.execute()
        Assert.assertEquals(expected, actual)

        cocktailDataToDomainMapper = BaseCocktailDataToDomainMapper()
        useCase = FetchCocktailsFromNetworkUseCase(
            TestCocktailsRepository(Exception()), BaseCocktailsDataToDomainMapper(cocktailDataToDomainMapper)
        )
        expected = CocktailsDomain.Fail(ErrorType.GENERIC_ERROR)
        actual = useCase.execute()
        Assert.assertEquals(expected, actual)
    }
}