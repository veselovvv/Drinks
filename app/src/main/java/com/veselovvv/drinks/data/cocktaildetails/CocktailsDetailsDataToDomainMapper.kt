package com.veselovvv.drinks.data.cocktaildetails

import com.veselovvv.drinks.core.Mapper
import com.veselovvv.drinks.domain.cocktaildetails.CocktailsDetailsDomain

abstract class CocktailsDetailsDataToDomainMapper :
    Mapper.DataToDomain.Base<CocktailDetailsData, CocktailsDetailsDomain>()
