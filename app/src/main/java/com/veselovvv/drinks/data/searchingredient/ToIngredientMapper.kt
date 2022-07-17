package com.veselovvv.drinks.data.searchingredient

import com.veselovvv.drinks.core.Mapper

interface ToIngredientMapper : Mapper {
    fun map(name: String, description: String, type: String, alcohol: String, abv: String): IngredientData

    class Base : ToIngredientMapper {
        override fun map(
            name: String, description: String, type: String, alcohol: String, abv: String
        ) = IngredientData(name, description, type, alcohol, abv)
    }
}