package com.veselovvv.drinks.data.cocktaildetails

import com.veselovvv.drinks.core.Object
import com.veselovvv.drinks.domain.cocktaildetails.CocktailsDetailsDomain

sealed class CocktailsDetailsData : Object<CocktailsDetailsDomain, CocktailsDetailsDataToDomainMapper> {
    data class Success(private val cocktailDetails: CocktailDetailsData) : CocktailsDetailsData() {
        override fun map(mapper: CocktailsDetailsDataToDomainMapper) = mapper.map(cocktailDetails)
    }

    data class Fail(private val exception: Exception) : CocktailsDetailsData() {
        override fun map(mapper: CocktailsDetailsDataToDomainMapper) = mapper.map(exception)
    }
}
