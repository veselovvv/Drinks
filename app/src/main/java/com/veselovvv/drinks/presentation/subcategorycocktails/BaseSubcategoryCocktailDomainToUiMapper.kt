package com.veselovvv.drinks.presentation.subcategorycocktails

import com.veselovvv.drinks.domain.subcategorycocktails.SubcategoryCocktailDomainToUiMapper

class BaseSubcategoryCocktailDomainToUiMapper : SubcategoryCocktailDomainToUiMapper {
    override fun map(name: String, photoUrl: String): SubcategoryCocktailUi =
        SubcategoryCocktailUi.Base(name, photoUrl)
}