package com.veselovvv.drinks.data.subcategorycocktails

import com.veselovvv.drinks.core.Object
import com.veselovvv.drinks.domain.subcategorycocktails.SubcategoryCocktailsDomain

sealed class SubcategoryCocktailsData : Object<SubcategoryCocktailsDomain, SubcategoryCocktailsDataToDomainMapper> {
    data class Success(private val cocktails: List<SubcategoryCocktailData>) : SubcategoryCocktailsData() {
        override fun map(mapper: SubcategoryCocktailsDataToDomainMapper) = mapper.map(cocktails)
    }

    data class Fail(private val exception: Exception) : SubcategoryCocktailsData() {
        override fun map(mapper: SubcategoryCocktailsDataToDomainMapper) = mapper.map(exception)
    }
}