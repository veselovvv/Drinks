package com.veselovvv.drinks.data.cocktaildetails

import com.veselovvv.drinks.core.Object
import com.veselovvv.drinks.data.cocktaildetails.cache.CocktailDetailsDataToDbMapper
import com.veselovvv.drinks.data.cocktaildetails.cache.CocktailDetailsDb
import com.veselovvv.drinks.domain.cocktaildetails.CocktailDetailsDomain

data class CocktailDetailsData(
    private val name: String,
    private val alcoholic: String,
    private val glass: String,
    private val instructions: String,
    private val ingredients: List<String>
) : Object<CocktailDetailsDomain, CocktailDetailsDataToDomainMapper>,
    Object.ToDb<CocktailDetailsDb, CocktailDetailsDataToDbMapper> {
    override fun map(mapper: CocktailDetailsDataToDomainMapper) =
        mapper.map(alcoholic, glass, instructions, ingredients)

    override fun mapWith(mapper: CocktailDetailsDataToDbMapper) =
        mapper.mapToDb(name, alcoholic, glass, instructions, ingredients)
}
