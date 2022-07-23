package com.veselovvv.drinks.domain.searchingredient

import com.veselovvv.drinks.core.Mapper
import com.veselovvv.drinks.core.ResourceProvider
import com.veselovvv.drinks.presentation.searchingredient.IngredientsUi

abstract class IngredientsDomainToUiMapper(resourceProvider: ResourceProvider) :
    Mapper.DomainToUi.Base<IngredientDomain, IngredientsUi>(resourceProvider) {
        abstract fun map(): IngredientsUi
}