package com.veselovvv.drinks.domain.subcategorycocktails

import com.veselovvv.drinks.core.Object
import com.veselovvv.drinks.presentation.subcategorycocktails.SubcategoryCocktailUi

data class SubcategoryCocktailDomain(
    private val id: String,
    private val name: String,
    private val photoUrl: String
) : Object<SubcategoryCocktailUi, SubcategoryCocktailDomainToUiMapper> {
    override fun map(mapper: SubcategoryCocktailDomainToUiMapper) = mapper.map(id, name, photoUrl)
}