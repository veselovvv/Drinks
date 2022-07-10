package com.veselovvv.drinks.data.randomcocktail

import com.veselovvv.drinks.core.Object
import com.veselovvv.drinks.domain.randomcocktail.RandomCocktailDomain

data class RandomCocktailData(
    private val name: String,
    private val category: String,
    private val alcoholic: String,
    private val glass: String,
    private val instructions: String,
    private val photoUrl: String,
    private val ingredients: List<String>
) : Object<RandomCocktailDomain, RandomCocktailDataToDomainMapper> {
    override fun map(mapper: RandomCocktailDataToDomainMapper) = mapper.map(
        name, category, alcoholic, glass, instructions, photoUrl, ingredients
    )
}