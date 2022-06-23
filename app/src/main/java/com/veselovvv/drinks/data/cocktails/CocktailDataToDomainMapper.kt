package com.veselovvv.drinks.data.cocktails

interface CocktailDataToDomainMapper {
    fun map(id: String, name: String, category: String, photoUrl: String): CocktailDomain
}