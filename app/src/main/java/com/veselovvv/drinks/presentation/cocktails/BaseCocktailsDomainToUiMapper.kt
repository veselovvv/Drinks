package com.veselovvv.drinks.presentation.cocktails

import com.veselovvv.drinks.core.ErrorType
import com.veselovvv.drinks.core.ResourceProvider
import com.veselovvv.drinks.domain.cocktails.CocktailDomain
import com.veselovvv.drinks.domain.cocktails.CocktailDomainToUiMapper
import com.veselovvv.drinks.domain.cocktails.CocktailsDomainToUiMapper

class BaseCocktailsDomainToUiMapper(
    resourceProvider: ResourceProvider,
    private val cocktailMapper: CocktailDomainToUiMapper
) : CocktailsDomainToUiMapper(resourceProvider) {
    override fun map(data: List<CocktailDomain>) = CocktailsUi.Success(data, cocktailMapper)
    override fun map(errorType: ErrorType) = CocktailsUi.Fail(getErrorMessage(errorType))
}