package com.veselovvv.drinks.data.cocktails

import com.veselovvv.drinks.core.Object
import java.lang.Exception

sealed class CocktailsData : Object<CocktailsDomain, CocktailsDataToDomainMapper> {
    data class Success(private val cocktails: List<CocktailData>) : CocktailsData() {
        override fun map(mapper: CocktailsDataToDomainMapper) = mapper.map(cocktails)
    }

    data class Fail(private val exception: Exception) : CocktailsData() {
        override fun map(mapper: CocktailsDataToDomainMapper) = mapper.map(exception)
    }
}