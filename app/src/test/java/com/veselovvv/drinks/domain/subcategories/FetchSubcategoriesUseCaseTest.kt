package com.veselovvv.drinks.domain.subcategories

import com.veselovvv.drinks.core.ErrorType
import com.veselovvv.drinks.data.subcategories.SubcategoryData
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test
import java.net.UnknownHostException

class FetchSubcategoriesUseCaseTest {
    @Test
    fun test_success() = runBlocking {
        val subcategories = listOf(
            SubcategoryData("Ordinary Drink"),
            SubcategoryData("Cocktail")
        )
        val subcategoryDataToDomainMapper = BaseSubcategoryDataToDomainMapper()
        val useCase = FetchSubcategoriesUseCase(
            TestSubcategoriesRepository(),
            BaseSubcategoriesDataToDomainMapper(subcategoryDataToDomainMapper)
        )
        val expected = SubcategoriesDomain.Success(subcategories, subcategoryDataToDomainMapper)
        val actual = useCase.execute("c")
        assertEquals(expected, actual)
    }

    @Test
    fun test_fail() = runBlocking {
        var subcategoryDataToDomainMapper = BaseSubcategoryDataToDomainMapper()
        var useCase = FetchSubcategoriesUseCase(
            TestSubcategoriesRepository(UnknownHostException()),
            BaseSubcategoriesDataToDomainMapper(subcategoryDataToDomainMapper)
        )
        var expected = SubcategoriesDomain.Fail(ErrorType.NO_CONNECTION)
        var actual = useCase.execute("c")
        assertEquals(expected, actual)

        subcategoryDataToDomainMapper = BaseSubcategoryDataToDomainMapper()
        useCase = FetchSubcategoriesUseCase(
            TestSubcategoriesRepository(Exception()),
            BaseSubcategoriesDataToDomainMapper(subcategoryDataToDomainMapper)
        )
        expected = SubcategoriesDomain.Fail(ErrorType.GENERIC_ERROR)
        actual = useCase.execute("c")
        assertEquals(expected, actual)
    }
}