package com.veselovvv.drinks.domain.cocktaildetails

import com.veselovvv.drinks.core.Object
import com.veselovvv.drinks.presentation.cocktaildetails.CocktailDetailsUi

data class CocktailDetailsDomain(
    private val alcoholic: String,
    private val glass: String,
    private val instructions: String,
    private val ingredients: List<String>
) : Object<CocktailDetailsUi, CocktailDetailsDomainToUiMapper> {
    override fun map(mapper: CocktailDetailsDomainToUiMapper) =
        mapper.map(alcoholic, glass, instructions, ingredients)
}