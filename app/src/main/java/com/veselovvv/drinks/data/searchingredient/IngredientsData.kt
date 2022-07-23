package com.veselovvv.drinks.data.searchingredient

import com.veselovvv.drinks.core.Object
import com.veselovvv.drinks.domain.searchingredient.IngredientsDomain

sealed class IngredientsData : Object<IngredientsDomain, IngredientsDataToDomainMapper> {
    data class Success(private val ingredient: IngredientData) : IngredientsData() {
        override fun map(mapper: IngredientsDataToDomainMapper) = mapper.map(ingredient)
    }

    data class Fail(private val exception: Exception) : IngredientsData() {
        override fun map(mapper: IngredientsDataToDomainMapper) = mapper.map(exception)
    }

    object NoResults : IngredientsData() {
        override fun map(mapper: IngredientsDataToDomainMapper) = mapper.map()
    }
}