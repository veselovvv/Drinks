package com.veselovvv.drinks.data.cocktails

import com.veselovvv.drinks.core.Mapper
import com.veselovvv.drinks.domain.cocktails.CocktailsDomain

abstract class CocktailsDataToDomainMapper : Mapper.DataToDomain.Base<List<CocktailData>, CocktailsDomain>()