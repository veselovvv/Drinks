package com.veselovvv.drinks.domain.randomcocktail

import com.veselovvv.drinks.data.randomcocktail.RandomCocktailData
import com.veselovvv.drinks.data.randomcocktail.RandomCocktailDataToDomainMapper
import com.veselovvv.drinks.data.randomcocktail.RandomCocktailsDataToDomainMapper

class BaseRandomCocktailsDataToDomainMapper(
    private val randomCocktailMapper: RandomCocktailDataToDomainMapper
) : RandomCocktailsDataToDomainMapper() {
    override fun map(data: RandomCocktailData) = RandomCocktailsDomain.Success(data, randomCocktailMapper)
    override fun map(e: Exception) = RandomCocktailsDomain.Fail(getErrorType(e))
}