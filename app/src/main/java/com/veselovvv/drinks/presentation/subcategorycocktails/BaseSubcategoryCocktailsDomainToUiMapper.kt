package com.veselovvv.drinks.presentation.subcategorycocktails

import com.veselovvv.drinks.core.ErrorType
import com.veselovvv.drinks.core.ResourceProvider
import com.veselovvv.drinks.domain.subcategorycocktails.SubcategoryCocktailDomain
import com.veselovvv.drinks.domain.subcategorycocktails.SubcategoryCocktailDomainToUiMapper
import com.veselovvv.drinks.domain.subcategorycocktails.SubcategoryCocktailsDomainToUiMapper

class BaseSubcategoryCocktailsDomainToUiMapper(
    resourceProvider: ResourceProvider,
    private val mapper: SubcategoryCocktailDomainToUiMapper
) : SubcategoryCocktailsDomainToUiMapper(resourceProvider) {
    override fun map(data: List<SubcategoryCocktailDomain>) = SubcategoryCocktailsUi.Success(data, mapper)
    override fun map(errorType: ErrorType) = SubcategoryCocktailsUi.Fail(getErrorMessage(errorType))
}