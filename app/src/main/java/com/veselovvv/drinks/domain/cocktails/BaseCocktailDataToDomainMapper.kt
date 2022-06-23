package com.veselovvv.drinks.domain.cocktails

import com.veselovvv.drinks.data.cocktails.CocktailDataToDomainMapper

class BaseCocktailDataToDomainMapper : CocktailDataToDomainMapper {
    override fun map(id: String, name: String, category: String, photoUrl: String) =
        CocktailDomain(id, name, category, photoUrl)
}