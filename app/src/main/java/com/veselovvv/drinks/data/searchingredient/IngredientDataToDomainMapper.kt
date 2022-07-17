package com.veselovvv.drinks.data.searchingredient

import com.veselovvv.drinks.core.Mapper

interface IngredientDataToDomainMapper : Mapper {
    fun map(
        name: String, description: String, type: String, alcohol: String, abv: String
    ): IngredientDomain
}