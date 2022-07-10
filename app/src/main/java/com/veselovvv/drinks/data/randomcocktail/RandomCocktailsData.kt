package com.veselovvv.drinks.data.randomcocktail

import com.veselovvv.drinks.core.Object
import com.veselovvv.drinks.domain.randomcocktail.RandomCocktailsDomain

sealed class RandomCocktailsData : Object<RandomCocktailsDomain, RandomCocktailsDataToDomainMapper> {
    data class Success(private val randomCocktail: RandomCocktailData) : RandomCocktailsData() {
        override fun map(mapper: RandomCocktailsDataToDomainMapper) = mapper.map(randomCocktail)
    }

    data class Fail(private val exception: Exception) : RandomCocktailsData() {
        override fun map(mapper: RandomCocktailsDataToDomainMapper) = mapper.map(exception)
    }
}