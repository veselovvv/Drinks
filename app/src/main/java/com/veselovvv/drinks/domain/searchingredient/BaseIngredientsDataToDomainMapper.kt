package com.veselovvv.drinks.domain.searchingredient

import com.veselovvv.drinks.data.searchingredient.IngredientData
import com.veselovvv.drinks.data.searchingredient.IngredientDataToDomainMapper
import com.veselovvv.drinks.data.searchingredient.IngredientsDataToDomainMapper

class BaseIngredientsDataToDomainMapper(
    private val ingredientMapper: IngredientDataToDomainMapper
) : IngredientsDataToDomainMapper() {
    override fun map(data: IngredientData) = IngredientsDomain.Success(data, ingredientMapper)
    override fun map(e: Exception) = IngredientsDomain.Fail(getErrorType(e))
}