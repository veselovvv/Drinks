package com.veselovvv.drinks.data.cocktails

import com.veselovvv.drinks.core.Mapper
import com.veselovvv.drinks.domain.cocktails.CocktailDomain

interface CocktailDataToDomainMapper : Mapper {
    fun map(name: String, category: String, photoUrl: String): CocktailDomain
}