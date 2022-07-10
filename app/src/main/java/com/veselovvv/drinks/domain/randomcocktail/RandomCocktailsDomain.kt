package com.veselovvv.drinks.domain.randomcocktail

import com.veselovvv.drinks.core.ErrorType
import com.veselovvv.drinks.core.Object
import com.veselovvv.drinks.data.randomcocktail.RandomCocktailData
import com.veselovvv.drinks.data.randomcocktail.RandomCocktailDataToDomainMapper
import com.veselovvv.drinks.presentation.randomcocktail.RandomCocktailsUi

sealed class RandomCocktailsDomain : Object<RandomCocktailsUi, RandomCocktailsDomainToUiMapper> {
    data class Success(
        private val randomCocktail: RandomCocktailData,
        private val randomCocktailMapper: RandomCocktailDataToDomainMapper
    ) : RandomCocktailsDomain() {
        override fun map(mapper: RandomCocktailsDomainToUiMapper) =
            mapper.map(randomCocktail.map(randomCocktailMapper))
    }

    data class Fail(private val errorType: ErrorType) : RandomCocktailsDomain() {
        override fun map(mapper: RandomCocktailsDomainToUiMapper) = mapper.map(errorType)
    }
}