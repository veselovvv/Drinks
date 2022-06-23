package com.veselovvv.drinks.data.cocktails

import java.lang.Exception

sealed class CocktailsData {
    data class Success(private val cocktails: List<CocktailData>) : CocktailsData() {
        fun map(mapper: CocktailsDataToDomainMapper) = mapper.map(cocktails)
    }

    data class Fail(private val exception: Exception) : CocktailsData() {
        fun map(mapper: CocktailsDataToDomainMapper) = mapper.map(exception)
    }
}