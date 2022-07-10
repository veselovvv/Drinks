package com.veselovvv.drinks.data.randomcocktail

import com.veselovvv.drinks.core.Mapper
import com.veselovvv.drinks.domain.randomcocktail.RandomCocktailDomain

interface RandomCocktailDataToDomainMapper : Mapper {
    fun map(
        name: String, category: String, alcoholic: String, glass: String,
        instructions: String, photoUrl: String, ingredients: List<String>
    ): RandomCocktailDomain
}