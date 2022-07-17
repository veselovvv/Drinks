package com.veselovvv.drinks.data.searchingredient.cloud

import com.veselovvv.drinks.core.Mapper
import com.veselovvv.drinks.data.searchingredient.IngredientData
import com.veselovvv.drinks.data.searchingredient.ToIngredientMapper

interface IngredientCloudMapper : Mapper.Data<IngredientCloud, IngredientData> {
    override fun map(data: IngredientCloud): IngredientData

    class Base(private val toIngredientMapper: ToIngredientMapper) : IngredientCloudMapper {
        override fun map(data: IngredientCloud) = data.map(toIngredientMapper)
    }
}