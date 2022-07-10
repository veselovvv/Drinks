package com.veselovvv.drinks.presentation.randomcocktail

import com.veselovvv.drinks.core.ErrorType
import com.veselovvv.drinks.core.ResourceProvider
import com.veselovvv.drinks.domain.randomcocktail.RandomCocktailDomain
import com.veselovvv.drinks.domain.randomcocktail.RandomCocktailDomainToUiMapper
import com.veselovvv.drinks.domain.randomcocktail.RandomCocktailsDomainToUiMapper

class BaseRandomCocktailsDomainToUiMapper(
    resourceProvider: ResourceProvider,
    private val randomCocktailMapper: RandomCocktailDomainToUiMapper
) : RandomCocktailsDomainToUiMapper(resourceProvider) {
    override fun map(data: RandomCocktailDomain) = RandomCocktailsUi.Success(data, randomCocktailMapper)
    override fun map(errorType: ErrorType) = RandomCocktailsUi.Fail(getErrorMessage(errorType))
}