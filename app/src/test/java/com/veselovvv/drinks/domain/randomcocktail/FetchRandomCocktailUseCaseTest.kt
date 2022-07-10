package com.veselovvv.drinks.domain.randomcocktail

import com.veselovvv.drinks.core.ErrorType
import com.veselovvv.drinks.data.randomcocktail.RandomCocktailData
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test
import java.net.UnknownHostException

class FetchRandomCocktailUseCaseTest {
    @Test
    fun test_success() = runBlocking {
        val randomCocktail = RandomCocktailData(
            "Margarita",
            "Cocktail",
            "Alcoholic",
            "Cocktail glass",
            "Rub the rim of the glass with the lime slice to make the salt stick to it.",
            "https://somephotopath1",
            listOf("Tequila", "Triple sec", "Lime juice", "Salt", "", "", "", "", "", "")
        )
        val randomCocktailDataToDomainMapper = BaseRandomCocktailDataToDomainMapper()
        val useCase = FetchRandomCocktailUseCase(
            TestRandomCocktailsRepository(),
            BaseRandomCocktailsDataToDomainMapper(randomCocktailDataToDomainMapper)
        )
        val expected = RandomCocktailsDomain.Success(randomCocktail, randomCocktailDataToDomainMapper)
        val actual = useCase.execute()
        assertEquals(expected, actual)
    }

    @Test
    fun test_fail() = runBlocking {
        val randomCocktailDataToDomainMapper = BaseRandomCocktailDataToDomainMapper()
        var useCase = FetchRandomCocktailUseCase(
            TestRandomCocktailsRepository(UnknownHostException()),
            BaseRandomCocktailsDataToDomainMapper(randomCocktailDataToDomainMapper)
        )
        var expected = RandomCocktailsDomain.Fail(ErrorType.NO_CONNECTION)
        var actual = useCase.execute()
        assertEquals(expected, actual)

        useCase = FetchRandomCocktailUseCase(
            TestRandomCocktailsRepository(Exception()),
            BaseRandomCocktailsDataToDomainMapper(randomCocktailDataToDomainMapper)
        )
        expected = RandomCocktailsDomain.Fail(ErrorType.GENERIC_ERROR)
        actual = useCase.execute()
        assertEquals(expected, actual)
    }
}