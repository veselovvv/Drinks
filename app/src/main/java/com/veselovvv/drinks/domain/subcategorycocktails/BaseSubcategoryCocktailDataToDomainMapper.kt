package com.veselovvv.drinks.domain.subcategorycocktails

import com.veselovvv.drinks.data.subcategorycocktails.SubcategoryCocktailDataToDomainMapper

class BaseSubcategoryCocktailDataToDomainMapper : SubcategoryCocktailDataToDomainMapper {
    override fun map(name: String, photoUrl: String) = SubcategoryCocktailDomain(name, photoUrl)
}