package com.veselovvv.drinks.domain.randomcocktail

import com.veselovvv.drinks.core.Mapper
import com.veselovvv.drinks.core.ResourceProvider
import com.veselovvv.drinks.presentation.randomcocktail.RandomCocktailsUi

abstract class RandomCocktailsDomainToUiMapper(resourceProvider: ResourceProvider) :
        Mapper.DomainToUi.Base<RandomCocktailDomain, RandomCocktailsUi>(resourceProvider)