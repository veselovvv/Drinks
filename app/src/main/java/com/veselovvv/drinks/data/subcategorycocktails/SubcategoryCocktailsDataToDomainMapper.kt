package com.veselovvv.drinks.data.subcategorycocktails

import com.veselovvv.drinks.core.Mapper
import com.veselovvv.drinks.domain.subcategorycocktails.SubcategoryCocktailsDomain

abstract class SubcategoryCocktailsDataToDomainMapper :
    Mapper.DataToDomain.Base<List<SubcategoryCocktailData>, SubcategoryCocktailsDomain>()