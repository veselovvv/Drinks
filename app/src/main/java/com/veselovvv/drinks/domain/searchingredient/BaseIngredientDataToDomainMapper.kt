package com.veselovvv.drinks.domain.searchingredient

import com.veselovvv.drinks.data.searchingredient.IngredientDataToDomainMapper

class BaseIngredientDataToDomainMapper : IngredientDataToDomainMapper {
    override fun map(
        name: String, description: String, type: String, alcohol: String, abv: String
    ) = IngredientDomain(name, description, type, alcohol, abv)
}