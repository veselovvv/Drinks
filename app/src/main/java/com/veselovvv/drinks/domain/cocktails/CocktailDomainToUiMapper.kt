package com.veselovvv.drinks.domain.cocktails

import com.veselovvv.drinks.core.Mapper

interface CocktailDomainToUiMapper : Mapper {
    fun map(id: String, name: String, category: String, photoUrl: String): CocktailUi
}