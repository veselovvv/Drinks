package com.veselovvv.drinks.domain.cocktaildetails

import com.veselovvv.drinks.data.cocktaildetails.CocktailDetailsData

interface CocktailDetailsRepository {
    suspend fun fetchCocktailDetails(cocktailName: String): CocktailDetailsData
}