package com.veselovvv.drinks.data.searchingredient

import com.veselovvv.drinks.core.Object

data class IngredientData(
    private val name: String,
    private val description: String,
    private val type: String,
    private val alcohol: String,
    private val abv: String
) : Object<IngredientDomain, IngredientDataToDomainMapper> {
    override fun map(mapper: IngredientDataToDomainMapper) =
        mapper.map(name, description, type, alcohol, abv)
}