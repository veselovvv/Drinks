package com.veselovvv.drinks.presentation.subcategorycocktails

import com.veselovvv.drinks.core.Object
import com.veselovvv.drinks.domain.subcategorycocktails.SubcategoryCocktailDomain
import com.veselovvv.drinks.domain.subcategorycocktails.SubcategoryCocktailDomainToUiMapper

sealed class SubcategoryCocktailsUi : Object<Unit, SubcategoryCocktailsCommunication> {
    data class Success(
        private val cocktails: List<SubcategoryCocktailDomain>,
        private val cocktailMapper: SubcategoryCocktailDomainToUiMapper
    ) : SubcategoryCocktailsUi() {
        override fun map(mapper: SubcategoryCocktailsCommunication) {
            val subcategoryCocktailsUi = cocktails.map { cocktail ->
                cocktail.map(cocktailMapper)
            }
            mapper.map(subcategoryCocktailsUi)
        }
    }

    data class Fail(private val errorMessage: String) : SubcategoryCocktailsUi() {
        override fun map(mapper: SubcategoryCocktailsCommunication) =
            mapper.map(listOf(SubcategoryCocktailUi.Fail(errorMessage)))
    }
}
