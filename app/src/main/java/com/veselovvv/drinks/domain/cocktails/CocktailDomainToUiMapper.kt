package com.veselovvv.drinks.domain.cocktails

import com.veselovvv.drinks.core.Mapper
import com.veselovvv.drinks.presentation.cocktails.CocktailUi

interface CocktailDomainToUiMapper : Mapper {
    fun map(id: String, name: String, category: String, photoUrl: String): CocktailUi
}