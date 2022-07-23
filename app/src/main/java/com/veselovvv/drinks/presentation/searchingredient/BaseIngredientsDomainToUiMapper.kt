package com.veselovvv.drinks.presentation.searchingredient

import com.veselovvv.drinks.core.ErrorType
import com.veselovvv.drinks.core.ResourceProvider
import com.veselovvv.drinks.domain.searchingredient.IngredientDomain
import com.veselovvv.drinks.domain.searchingredient.IngredientDomainToUiMapper
import com.veselovvv.drinks.domain.searchingredient.IngredientsDomainToUiMapper

class BaseIngredientsDomainToUiMapper(
    resourceProvider: ResourceProvider,
    private val ingredientMapper: IngredientDomainToUiMapper
) : IngredientsDomainToUiMapper(resourceProvider) {
    override fun map(data: IngredientDomain) = IngredientsUi.Success(data, ingredientMapper)
    override fun map(errorType: ErrorType) = IngredientsUi.Fail(getErrorMessage(errorType))
    override fun map() = IngredientsUi.NoResults
}