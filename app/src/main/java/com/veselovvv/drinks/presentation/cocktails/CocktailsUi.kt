package com.veselovvv.drinks.presentation.cocktails

import com.veselovvv.drinks.core.ErrorType
import com.veselovvv.drinks.core.Object
import com.veselovvv.drinks.domain.cocktails.CocktailDomain
import com.veselovvv.drinks.domain.cocktails.CocktailDomainToUiMapper
import com.veselovvv.drinks.domain.cocktails.CocktailsDomainToUiMapper

sealed class CocktailsUi : Object<Unit, CocktailsCommunication> {
    class Success(
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

    class Fail(
        private val errorType: ErrorType,
        private val cocktailMapper: CocktailsDomainToUiMapper
    ) : CocktailsUi() {
        override fun map(mapper: CocktailsCommunication) =
            mapper.map(listOf(CocktailUi.Fail(cocktailMapper.getErrorMessage(errorType))))
    }
}