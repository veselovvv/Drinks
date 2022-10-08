package com.veselovvv.drinks.data.cocktails

import com.veselovvv.drinks.core.Object
import com.veselovvv.drinks.data.cocktails.cache.CocktailDataToDbMapper
import com.veselovvv.drinks.data.cocktails.cache.CocktailDb
import com.veselovvv.drinks.domain.cocktails.CocktailDomain

data class CocktailData(
    private val id: String,
    private val name: String,
    private val category: String,
    private val photoUrl: String
) : Object<CocktailDomain, CocktailDataToDomainMapper>, Object.ToDb<CocktailDb, CocktailDataToDbMapper> {
    override fun map(mapper: CocktailDataToDomainMapper) = mapper.map(name, category, photoUrl)
    override fun mapWith(mapper: CocktailDataToDbMapper) = mapper.mapToDb(id, name, category, photoUrl)
}
