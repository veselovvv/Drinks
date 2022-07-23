package com.veselovvv.drinks.presentation.searchingredient

import com.veselovvv.drinks.domain.searchingredient.IngredientDomainToUiMapper

class BaseIngredientDomainToUiMapper : IngredientDomainToUiMapper {
    override fun map(
        name: String, description: String, type: String, alcohol: String, abv: String
    ) = IngredientUi.Base(name, description, type, alcohol, abv)
}