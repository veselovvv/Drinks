package com.veselovvv.drinks.data.cocktails

import com.veselovvv.drinks.core.Object
import com.veselovvv.drinks.domain.cocktails.CocktailDomain

data class CocktailData(
    private val id: String,
    private val name: String,
    private val category: String,
    private val photoUrl: String
) : Object<CocktailDomain, CocktailDataToDomainMapper> {
    override fun map(mapper: CocktailDataToDomainMapper) = mapper.map(id, name, category, photoUrl)
}
