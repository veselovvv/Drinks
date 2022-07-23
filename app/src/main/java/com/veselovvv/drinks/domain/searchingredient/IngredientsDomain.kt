package com.veselovvv.drinks.domain.searchingredient

import com.veselovvv.drinks.core.ErrorType
import com.veselovvv.drinks.core.Object
import com.veselovvv.drinks.data.searchingredient.IngredientData
import com.veselovvv.drinks.data.searchingredient.IngredientDataToDomainMapper
import com.veselovvv.drinks.presentation.searchingredient.IngredientsUi

sealed class IngredientsDomain : Object<IngredientsUi, IngredientsDomainToUiMapper> {
    data class Success(
        private val ingredient: IngredientData,
        private val ingredientMapper: IngredientDataToDomainMapper
    ) : IngredientsDomain() {
        override fun map(mapper: IngredientsDomainToUiMapper) =
            mapper.map(ingredient.map(ingredientMapper))
    }

    data class Fail(private val errorType: ErrorType) : IngredientsDomain() {
        override fun map(mapper: IngredientsDomainToUiMapper) = mapper.map(errorType)
    }

    object NoResults : IngredientsDomain() {
        override fun map(mapper: IngredientsDomainToUiMapper) = mapper.map()
    }
}