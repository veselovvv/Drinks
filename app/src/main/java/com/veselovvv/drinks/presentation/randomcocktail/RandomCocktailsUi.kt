package com.veselovvv.drinks.presentation.randomcocktail

import com.veselovvv.drinks.core.Object
import com.veselovvv.drinks.domain.randomcocktail.RandomCocktailDomain
import com.veselovvv.drinks.domain.randomcocktail.RandomCocktailDomainToUiMapper

sealed class RandomCocktailsUi : Object<Unit, RandomCocktailCommunication> {
    data class Success(
        private val randomCocktail: RandomCocktailDomain,
        private val randomCocktailMapper: RandomCocktailDomainToUiMapper
    ) : RandomCocktailsUi() {
        override fun map(mapper: RandomCocktailCommunication) {
            val randomCocktailUi = randomCocktail.map(randomCocktailMapper)
            mapper.map(randomCocktailUi)
        }
    }

    data class Fail(private val errorMessage: String) : RandomCocktailsUi() {
        override fun map(mapper: RandomCocktailCommunication) =
            mapper.map(RandomCocktailUi.Fail(errorMessage))
    }
}