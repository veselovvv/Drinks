package com.veselovvv.drinks.presentation.cocktails

import com.veselovvv.drinks.core.Object
import com.veselovvv.drinks.domain.cocktails.CocktailDomain
import com.veselovvv.drinks.domain.cocktails.CocktailDomainToUiMapper

sealed class CocktailsUi : Object<Unit, CocktailsCommunication> {
    data class Success(
        private val cocktails: List<CocktailDomain>,
        private val cocktailMapper: CocktailDomainToUiMapper
    ) : CocktailsUi() {
        override fun map(mapper: CocktailsCommunication) {
            val cocktailsUi = cocktails.map { cocktail ->
                cocktail.map(cocktailMapper)
            }
            mapper.map(cocktailsUi)
        }
    }

    data class Fail(
        private val errorMessage: String
    ) : CocktailsUi() {
        override fun map(mapper: CocktailsCommunication) =
            mapper.map(listOf(CocktailUi.Fail(errorMessage)))
    }
}