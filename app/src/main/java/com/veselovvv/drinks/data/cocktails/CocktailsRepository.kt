package com.veselovvv.drinks.data.cocktails

import com.veselovvv.drinks.data.cocktails.cache.CocktailsCacheDataSource
import com.veselovvv.drinks.data.cocktails.cache.CocktailsCacheMapper
import com.veselovvv.drinks.data.cocktails.cloud.CocktailsCloudDataSource
import com.veselovvv.drinks.data.cocktails.cloud.CocktailsCloudMapper

interface CocktailsRepository {
    suspend fun fetchCocktails(): CocktailsData
    suspend fun searchCocktails(query: String): CocktailsData

    class Base(
        private val cloudDataSource: CocktailsCloudDataSource,
        private val cacheDataSource: CocktailsCacheDataSource,
        private val cocktailsCloudMapper: CocktailsCloudMapper,
        private val cocktailsCacheMapper: CocktailsCacheMapper
    ) : CocktailsRepository {
        override suspend fun fetchCocktails() = try {
            val cocktailsCacheList = cacheDataSource.read()
            if (cocktailsCacheList.isEmpty()) {
                val cocktailsCloudList = cloudDataSource.fetchCocktails()
                val cocktails = cocktailsCloudMapper.map(cocktailsCloudList)
                cacheDataSource.save(cocktails)
                CocktailsData.Success(cocktails)
            } else CocktailsData.Success(cocktailsCacheMapper.map(cocktailsCacheList))
        } catch (e: Exception) {
            CocktailsData.Fail(e)
        }

        // TODO change search with db and network
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