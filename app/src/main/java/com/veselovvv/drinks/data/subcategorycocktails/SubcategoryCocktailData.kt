package com.veselovvv.drinks.data.subcategorycocktails

import com.veselovvv.drinks.core.Object
import com.veselovvv.drinks.domain.subcategorycocktails.SubcategoryCocktailDomain

data class SubcategoryCocktailData(
    private val name: String,
    private val photoUrl: String
) : Object<SubcategoryCocktailDomain, SubcategoryCocktailDataToDomainMapper> {
    override fun map(mapper: SubcategoryCocktailDataToDomainMapper) = mapper.map(name, photoUrl)
}