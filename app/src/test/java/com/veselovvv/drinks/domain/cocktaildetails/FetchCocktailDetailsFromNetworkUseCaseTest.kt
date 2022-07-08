package com.veselovvv.drinks.domain.cocktaildetails

import com.veselovvv.drinks.core.ErrorType
import com.veselovvv.drinks.data.cocktaildetails.CocktailDetailsData
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test
import java.net.UnknownHostException

class FetchCocktailDetailsFromNetworkUseCaseTest {
    @Test
    fun test_success() = runBlocking {
        val cocktailDetails = CocktailDetailsData(
            "Margarita",
            "Alcoholic",
            "Cocktail glass",
            "Rub the rim of the glass with the lime slice to make the salt stick to it.",
            listOf("Tequila", "Triple sec", "Lime juice", "Salt", "", "", "", "", "", "")
        )
        val cocktailDetailsDataToDomainMapper = BaseCocktailDetailsDataToDomainMapper()
        val useCase = FetchCocktailDetailsFromNetworkUseCase(
            TestCocktailsDetailsRepository(),
            BaseCocktailsDetailsDataToDomainMapper(cocktailDetailsDataToDomainMapper)
        )
        val expected = CocktailsDetailsDomain.Success(cocktailDetails, cocktailDetailsDataToDomainMapper)
        val actual = useCase.execute("Margarita")
        Assert.assertEquals(expected, actual)
    }

    @Test
    fun test_fail() = runBlocking {
        val cocktailDetailsDataToDomainMapper = BaseCocktailDetailsDataToDomainMapper()
        var useCase = FetchCocktailDetailsFromNetworkUseCase(
            TestCocktailsDetailsRepository(UnknownHostException()),
            BaseCocktailsDetailsDataToDomainMapper(cocktailDetailsDataToDomainMapper)
        )
        var expected = CocktailsDetailsDomain.Fail(ErrorType.NO_CONNECTION)
        var actual = useCase.execute("")
        Assert.assertEquals(expected, actual)

        useCase = FetchCocktailDetailsFromNetworkUseCase(
            TestCocktailsDetailsRepository(Exception()),
            BaseCocktailsDetailsDataToDomainMapper(cocktailDetailsDataToDomainMapper)
        )
        expected = CocktailsDetailsDomain.Fail(ErrorType.GENERIC_ERROR)
        actual = useCase.execute("")
        Assert.assertEquals(expected, actual)
    }
}