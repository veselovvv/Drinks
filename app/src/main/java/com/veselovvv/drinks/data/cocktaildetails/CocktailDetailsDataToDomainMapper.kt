package com.veselovvv.drinks.data.cocktaildetails

import com.veselovvv.drinks.core.Mapper

interface CocktailDetailsDataToDomainMapper : Mapper {
    fun map(
        alcoholic: String, glass: String, instructions: String, ingredients: List<String>
    ): CocktailDetailsDomain
}