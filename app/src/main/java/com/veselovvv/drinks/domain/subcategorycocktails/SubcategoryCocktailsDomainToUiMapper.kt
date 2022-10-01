package com.veselovvv.drinks.domain.subcategorycocktails

import com.veselovvv.drinks.core.Mapper
import com.veselovvv.drinks.core.ResourceProvider
import com.veselovvv.drinks.presentation.subcategorycocktails.SubcategoryCocktailsUi

abstract class SubcategoryCocktailsDomainToUiMapper(resourceProvider: ResourceProvider) :
    Mapper.DomainToUi.Base<List<SubcategoryCocktailDomain>, SubcategoryCocktailsUi>(resourceProvider)