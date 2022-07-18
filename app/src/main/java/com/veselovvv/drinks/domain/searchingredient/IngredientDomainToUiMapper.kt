package com.veselovvv.drinks.domain.searchingredient

import com.veselovvv.drinks.core.Mapper

interface IngredientDomainToUiMapper : Mapper {
    fun map(
        name: String, description: String, type: String, alcohol: String, abv: String
    ): IngredientUi
}