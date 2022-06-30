package com.veselovvv.drinks.domain.cocktaildetails

import com.veselovvv.drinks.core.ErrorType
import com.veselovvv.drinks.core.Object
import com.veselovvv.drinks.data.cocktaildetails.CocktailDetailsData
import com.veselovvv.drinks.data.cocktaildetails.CocktailDetailsDataToDomainMapper
import com.veselovvv.drinks.presentation.cocktaildetails.CocktailsDetailsUi

sealed class CocktailsDetailsDomain : Object<CocktailsDetailsUi, CocktailsDetailsDomainToUiMapper> {
    data class Success(
        private val cocktailDetails: CocktailDetailsData,
        private val cocktailDetailsMapper: CocktailDetailsDataToDomainMapper
    ) : CocktailsDetailsDomain() {
        override fun map(mapper: CocktailsDetailsDomainToUiMapper) =
            mapper.map(cocktailDetails.map(cocktailDetailsMapper))
    }

    data class Fail(private val errorType: ErrorType) : CocktailsDetailsDomain() {
        override fun map(mapper: CocktailsDetailsDomainToUiMapper) = mapper.map(errorType)
    }
}