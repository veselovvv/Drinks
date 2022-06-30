package com.veselovvv.drinks.domain.cocktaildetails

import com.veselovvv.drinks.core.Mapper
import com.veselovvv.drinks.presentation.cocktaildetails.CocktailDetailsUi

interface CocktailDetailsDomainToUiMapper : Mapper {
    fun map(
        alcoholic: String, glass: String, instructions: String, ingredients: List<String>
    ): CocktailDetailsUi
}