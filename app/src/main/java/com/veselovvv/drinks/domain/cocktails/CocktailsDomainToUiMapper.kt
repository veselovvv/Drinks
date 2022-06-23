package com.veselovvv.drinks.domain.cocktails

import com.veselovvv.drinks.core.Mapper
import com.veselovvv.drinks.core.ResourceProvider

abstract class CocktailsDomainToUiMapper(resourceProvider: ResourceProvider) :
        Mapper.DomainToUi.Base<List<CocktailDomain>, CocktailsUi>(resourceProvider)