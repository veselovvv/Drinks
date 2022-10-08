package com.veselovvv.drinks.domain.cocktails

import com.veselovvv.drinks.data.cocktails.CocktailDataToDomainMapper

class BaseCocktailDataToDomainMapper : CocktailDataToDomainMapper {
    override fun map(name: String, category: String, photoUrl: String) =
        CocktailDomain(name, category, photoUrl)
}