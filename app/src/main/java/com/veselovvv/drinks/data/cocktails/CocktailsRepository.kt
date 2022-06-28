package com.veselovvv.drinks.data.cocktails

import com.veselovvv.drinks.data.cocktails.cloud.CocktailsCloudDataSource
import com.veselovvv.drinks.data.cocktails.cloud.CocktailsCloudMapper

interface CocktailsRepository {
    suspend fun fetchCocktails(): CocktailsData
    suspend fun searchCocktails(query: String): CocktailsData

    class Base(
        private val cloudDataSource: CocktailsCloudDataSource,
        private val cocktailsCloudMapper: CocktailsCloudMapper
    ) : CocktailsRepository {
        override suspend fun fetchCocktails() = try {
            val cocktailsCloudList = cloudDataSource.fetchCocktails()
            val cocktails = cocktailsCloudMapper.map(cocktailsCloudList)
            CocktailsData.Success(cocktails)
        } catch (e: Exception) {
            CocktailsData.Fail(e)
        }

        override suspend fun searchCocktails(query: String) = try {
            val cocktailsCloudList = cloudDataSource.fetchCocktails().filter { cocktail ->
                cocktail.getName().startsWith(query)
            }
            val cocktails = cocktailsCloudMapper.map(cocktailsCloudList)
            CocktailsData.Success(cocktails)
        } catch (e: Exception) {
            CocktailsData.Fail(e)
        }
    }
}