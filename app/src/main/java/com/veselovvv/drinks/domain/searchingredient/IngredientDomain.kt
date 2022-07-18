package com.veselovvv.drinks.domain.searchingredient

import com.veselovvv.drinks.core.Object

data class IngredientDomain(
    private val name: String,
    private val description: String,
    private val type: String,
    private val alcohol: String,
    private val abv: String
) : Object<IngredientUi, IngredientDomainToUiMapper> {
    override fun map(mapper: IngredientDomainToUiMapper) = mapper.map(name, description, type, alcohol, abv)
}