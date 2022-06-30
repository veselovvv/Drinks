package com.veselovvv.drinks.presentation.cocktaildetails

import com.veselovvv.drinks.core.ErrorType
import com.veselovvv.drinks.core.ResourceProvider
import com.veselovvv.drinks.domain.cocktaildetails.CocktailDetailsDomain
import com.veselovvv.drinks.domain.cocktaildetails.CocktailDetailsDomainToUiMapper
import com.veselovvv.drinks.domain.cocktaildetails.CocktailsDetailsDomainToUiMapper

class BaseCocktailsDetailsDomainToUiMapper(
    private val resourceProvider: ResourceProvider,
    private val cocktailDetailsMapper: CocktailDetailsDomainToUiMapper
) : CocktailsDetailsDomainToUiMapper(resourceProvider) {
    override fun map(data: CocktailDetailsDomain) =
        CocktailsDetailsUi.Success(data, cocktailDetailsMapper)
    override fun map(errorType: ErrorType) = CocktailsDetailsUi.Fail(getErrorMessage(errorType))
}