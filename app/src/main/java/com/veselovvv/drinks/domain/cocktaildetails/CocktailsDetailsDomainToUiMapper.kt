package com.veselovvv.drinks.domain.cocktaildetails

import com.veselovvv.drinks.core.Mapper
import com.veselovvv.drinks.core.ResourceProvider
import com.veselovvv.drinks.presentation.cocktaildetails.CocktailsDetailsUi

abstract class CocktailsDetailsDomainToUiMapper(resourceProvider: ResourceProvider) :
        Mapper.DomainToUi.Base<CocktailDetailsDomain, CocktailsDetailsUi>(resourceProvider)