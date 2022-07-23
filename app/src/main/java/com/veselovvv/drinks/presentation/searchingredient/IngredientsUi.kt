package com.veselovvv.drinks.presentation.searchingredient

import com.veselovvv.drinks.core.Object
import com.veselovvv.drinks.domain.searchingredient.IngredientDomain
import com.veselovvv.drinks.domain.searchingredient.IngredientDomainToUiMapper

sealed class IngredientsUi : Object<Unit, IngredientCommunication> {
    data class Success(
        private val ingredient: IngredientDomain,
        private val ingredientMapper: IngredientDomainToUiMapper
    ) : IngredientsUi() {
        override fun map(mapper: IngredientCommunication) {
            val ingredientUi = ingredient.map(ingredientMapper)
            mapper.map(ingredientUi)
        }
    }

    data class Fail(private val errorMessage: String) : IngredientsUi() {
        override fun map(mapper: IngredientCommunication) =
            mapper.map(IngredientUi.Fail(errorMessage))
    }

    object NoResults : IngredientsUi() {
        override fun map(mapper: IngredientCommunication) = mapper.map(IngredientUi.NoResults)
    }
}