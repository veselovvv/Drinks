package com.veselovvv.drinks.data.cocktaildetails

import com.veselovvv.drinks.core.Mapper

abstract class CocktailsDetailsDataToDomainMapper :
    Mapper.DataToDomain.Base<CocktailDetailsData, CocktailsDetailsDomain>()