package com.veselovvv.drinks.domain.cocktails

import com.veselovvv.drinks.core.ErrorType
import com.veselovvv.drinks.core.Object
import com.veselovvv.drinks.data.cocktails.CocktailData
import com.veselovvv.drinks.data.cocktails.CocktailDataToDomainMapper

sealed class CocktailsDomain : Object<CocktailsUi, CocktailsDomainToUiMapper> {
    data class Success(
        private val cocktails: List<CocktailData>,
        private val cocktailMapper:CocktailDataToDomainMapper
    ) : CocktailsDomain() {
        override fun map(mapper: CocktailsDomainToUiMapper) = mapper.map(cocktails.map { cocktail ->
            cocktail.map(cocktailMapper) }
        )
    }

    data class Fail(private val errorType: ErrorType) : CocktailsDomain() {
        override fun map(mapper: CocktailsDomainToUiMapper) = mapper.map(errorType)
    }
}