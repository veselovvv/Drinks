package com.veselovvv.drinks.presentation.cocktaildetails

import com.veselovvv.drinks.domain.cocktaildetails.CocktailDetailsDomainToUiMapper

class BaseCocktailDetailsDomainToUiMapper : CocktailDetailsDomainToUiMapper {
    override fun map(alcoholic: String, glass: String, instructions: String, ingredients: List<String>) =
        CocktailDetailsUi.Base(alcoholic, glass, instructions, ingredients)
}