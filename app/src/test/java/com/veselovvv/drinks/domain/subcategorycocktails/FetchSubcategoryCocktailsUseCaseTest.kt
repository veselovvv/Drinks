package com.veselovvv.drinks.domain.subcategorycocktails

import com.veselovvv.drinks.core.ErrorType
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test
import java.net.UnknownHostException

class FetchSubcategoryCocktailsUseCaseTest {
    @Test
    fun test_success() = runBlocking {
        val cocktails = listOf(
            SubcategoryCocktailData("1", "Margarita", "https://somephotopath1"),
            SubcategoryCocktailData("12", "Martini", "https://somephotopath2")
        )
        val subcategoryCocktailDataToDomainMapper = BaseSubcategoryCocktailDataToDomainMapper()
        val useCase = FetchSubcategoryCocktailsUseCase(
            TestSubcategoryCocktailsRepository(),
            BaseSubcategoryCocktailsDataToDomainMapper(subcategoryCocktailDataToDomainMapper)
        )
        val expected = SubcategoryCocktailsDomain.Success(cocktails, subcategoryCocktailDataToDomainMapper)
        val actual = useCase.execute("Alcoholic")
        assertEquals(expected, actual)
    }

    @Test
    fun test_fail() = runBlocking {
        var subcategoryCocktailDataToDomainMapper = BaseSubcategoryCocktailDataToDomainMapper()
        var useCase = FetchSubcategoryCocktailsUseCase(
            TestSubcategoryCocktailsRepository(UnknownHostException()),
            BaseSubcategoryCocktailsDataToDomainMapper(subcategoryCocktailDataToDomainMapper)
        )
        var expected = SubcategoryCocktailsDomain.Fail(ErrorType.NO_CONNECTION)
        var actual = useCase.execute("Alcoholic")
        assertEquals(expected, actual)

        subcategoryCocktailDataToDomainMapper = BaseSubcategoryCocktailDataToDomainMapper()
        useCase = FetchSubcategoryCocktailsUseCase(
            TestSubcategoryCocktailsRepository(Exception()),
            BaseSubcategoryCocktailsDataToDomainMapper(subcategoryCocktailDataToDomainMapper)
        )
        expected = SubcategoryCocktailsDomain.Fail(ErrorType.GENERIC_ERROR)
        actual = useCase.execute("Alcoholic")
        assertEquals(expected, actual)
    }
}