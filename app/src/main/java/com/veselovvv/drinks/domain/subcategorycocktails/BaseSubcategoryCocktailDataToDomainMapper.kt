package com.veselovvv.drinks.domain.subcategorycocktails

import com.veselovvv.drinks.data.subcategorycocktails.SubcategoryCocktailDataToDomainMapper

class BaseSubcategoryCocktailDataToDomainMapper : SubcategoryCocktailDataToDomainMapper {
    override fun map(id: String, name: String, photoUrl: String) =
        SubcategoryCocktailDomain(id, name, photoUrl)
}