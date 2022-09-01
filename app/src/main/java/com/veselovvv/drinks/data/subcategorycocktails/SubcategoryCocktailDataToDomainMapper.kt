package com.veselovvv.drinks.data.subcategorycocktails

import com.veselovvv.drinks.core.Mapper
import com.veselovvv.drinks.domain.subcategorycocktails.SubcategoryCocktailDomain

interface SubcategoryCocktailDataToDomainMapper : Mapper {
    fun map(id: String, name: String, photoUrl: String): SubcategoryCocktailDomain
}