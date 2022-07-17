package com.veselovvv.drinks.data.cocktails

import com.veselovvv.drinks.core.Object
import com.veselovvv.drinks.domain.cocktails.CocktailsDomain

sealed class CocktailsData : Object<CocktailsDomain, CocktailsDataToDomainMapper> {
    data class Success(private val cocktails: List<CocktailData>) : CocktailsData() {
        override fun map(mapper: CocktailsDataToDomainMapper) = mapper.map(cocktails)
    }

    data class Fail(private val exception: Exception) : CocktailsData() {
        override fun map(mapper: CocktailsDataToDomainMapper) = mapper.map(exception)
    }
}