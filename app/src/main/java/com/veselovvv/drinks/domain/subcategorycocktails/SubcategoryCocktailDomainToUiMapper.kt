package com.veselovvv.drinks.domain.subcategorycocktails

import com.veselovvv.drinks.core.Mapper
import com.veselovvv.drinks.presentation.subcategorycocktails.SubcategoryCocktailUi

interface SubcategoryCocktailDomainToUiMapper : Mapper {
    fun map(id: String, name: String, photoUrl: String): SubcategoryCocktailUi
}