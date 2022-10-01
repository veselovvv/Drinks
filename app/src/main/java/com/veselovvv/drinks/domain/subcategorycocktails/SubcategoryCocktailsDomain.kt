package com.veselovvv.drinks.domain.subcategorycocktails

import com.veselovvv.drinks.core.ErrorType
import com.veselovvv.drinks.core.Object
import com.veselovvv.drinks.data.subcategorycocktails.SubcategoryCocktailData
import com.veselovvv.drinks.data.subcategorycocktails.SubcategoryCocktailDataToDomainMapper
import com.veselovvv.drinks.presentation.subcategorycocktails.SubcategoryCocktailsUi

sealed class SubcategoryCocktailsDomain :
    Object<SubcategoryCocktailsUi, SubcategoryCocktailsDomainToUiMapper> {
    data class Success(
        private val cocktails: List<SubcategoryCocktailData>,
        private val cocktailMapper: SubcategoryCocktailDataToDomainMapper
    ) : SubcategoryCocktailsDomain() {
        override fun map(mapper: SubcategoryCocktailsDomainToUiMapper) = mapper.map(cocktails.map { cocktail ->
            cocktail.map(cocktailMapper)
        })
    }

    data class Fail(private val errorType: ErrorType) : SubcategoryCocktailsDomain() {
        override fun map(mapper: SubcategoryCocktailsDomainToUiMapper) = mapper.map(errorType)
    }
}
