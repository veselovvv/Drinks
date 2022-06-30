package com.veselovvv.drinks.presentation.cocktaildetails

import com.veselovvv.drinks.core.Object
import com.veselovvv.drinks.domain.cocktaildetails.CocktailDetailsDomain
import com.veselovvv.drinks.domain.cocktaildetails.CocktailDetailsDomainToUiMapper

sealed class CocktailsDetailsUi : Object<Unit, CocktailsDetailsCommunication> {
    data class Success(
        private val cocktailDetails: CocktailDetailsDomain,
        private val cocktailDetailsMapper: CocktailDetailsDomainToUiMapper
    ) : CocktailsDetailsUi() {
        override fun map(mapper: CocktailsDetailsCommunication) {
            val cocktailDetailsUi = cocktailDetails.map(cocktailDetailsMapper)
            mapper.map(cocktailDetailsUi)
        }
    }

    data class Fail(private val errorMessage: String) : CocktailsDetailsUi() {
        override fun map(mapper: CocktailsDetailsCommunication) =
            mapper.map(CocktailDetailsUi.Fail(errorMessage))
    }
}