package com.veselovvv.drinks.data.randomcocktail

import com.veselovvv.drinks.core.Mapper
import com.veselovvv.drinks.domain.randomcocktail.RandomCocktailsDomain

abstract class RandomCocktailsDataToDomainMapper :
    Mapper.DataToDomain.Base<RandomCocktailData, RandomCocktailsDomain>()