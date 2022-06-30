package com.veselovvv.drinks.domain.cocktaildetails

import com.veselovvv.drinks.data.cocktaildetails.CocktailDetailsDataToDomainMapper

class BaseCocktailDetailsDataToDomainMapper : CocktailDetailsDataToDomainMapper {
    override fun map(
        alcoholic: String,
        glass: String,
        instructions: String,
        ingredients: List<String>
    ) = CocktailDetailsDomain(alcoholic, glass, instructions, ingredients)
}