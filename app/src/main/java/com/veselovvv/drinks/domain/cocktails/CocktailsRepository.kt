package com.veselovvv.drinks.domain.cocktails

import com.veselovvv.drinks.data.cocktails.CocktailsData

interface CocktailsRepository {
    suspend fun fetchCocktails(): CocktailsData
    suspend fun searchCocktails(query: String): CocktailsData
}