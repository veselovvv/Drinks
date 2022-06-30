package com.veselovvv.drinks.data.cocktaildetails

import com.veselovvv.drinks.core.Object
import com.veselovvv.drinks.domain.cocktaildetails.CocktailDetailsDomain

data class CocktailDetailsData(
    private val alcoholic: String,
    private val glass: String,
    private val instructions: String,
    private val ingredients: List<String>
) : Object<CocktailDetailsDomain, CocktailDetailsDataToDomainMapper> {
    override fun map(mapper: CocktailDetailsDataToDomainMapper) =
        mapper.map(alcoholic, glass, instructions, ingredients)
}
