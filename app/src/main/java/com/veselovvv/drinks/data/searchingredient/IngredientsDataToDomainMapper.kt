package com.veselovvv.drinks.data.searchingredient

import com.veselovvv.drinks.core.Mapper
import com.veselovvv.drinks.domain.searchingredient.IngredientsDomain

abstract class IngredientsDataToDomainMapper :
    Mapper.DataToDomain.Base<IngredientData, IngredientsDomain>()