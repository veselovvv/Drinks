package com.veselovvv.drinks.data.cocktaildetails

import com.veselovvv.drinks.core.Mapper
import com.veselovvv.drinks.domain.cocktaildetails.CocktailDetailsDomain

interface CocktailDetailsDataToDomainMapper : Mapper {
    fun map(
        alcoholic: String, glass: String, instructions: String, ingredients: List<String>
    ): CocktailDetailsDomain
}