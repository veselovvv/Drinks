package com.veselovvv.drinks.data.searchingredient

import com.veselovvv.drinks.core.Mapper

abstract class IngredientsDataToDomainMapper :
    Mapper.DataToDomain.Base<IngredientData, IngredientsDomain>()