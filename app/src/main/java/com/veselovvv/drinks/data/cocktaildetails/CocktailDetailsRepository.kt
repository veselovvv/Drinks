package com.veselovvv.drinks.data.cocktaildetails

import com.veselovvv.drinks.data.cocktaildetails.cache.CocktailDetailsCacheDataSource
import com.veselovvv.drinks.data.cocktaildetails.cache.CocktailDetailsCacheMapper
import com.veselovvv.drinks.data.cocktaildetails.cloud.CocktailDetailsCloudDataSource
import com.veselovvv.drinks.data.cocktaildetails.cloud.CocktailDetailsCloudMapper

interface CocktailDetailsRepository {
    suspend fun fetchCocktailDetails(cocktailName: String): CocktailsDetailsData
    suspend fun fetchCocktailDetailsFromNetwork(cocktailName: String): CocktailsDetailsData

    class Base(
        private val cloudDataSource: CocktailDetailsCloudDataSource,
        private val cacheDataSource: CocktailDetailsCacheDataSource,
        private val cloudMapper: CocktailDetailsCloudMapper,
        private val cacheMapper: CocktailDetailsCacheMapper
    ) : CocktailDetailsRepository {
        override suspend fun fetchCocktailDetails(cocktailName: String) = try {
            val cocktailDetailsCache = cacheDataSource.read(cocktailName)
            if (cocktailDetailsCache == null) {
                val cocktailDetailsCloud = cloudDataSource.fetchCocktailDetails(cocktailName)
                val cocktailDetails = cloudMapper.map(cocktailDetailsCloud)
                cacheDataSource.save(cocktailDetails)
                CocktailsDetailsData.Success(cocktailDetails)
            } else CocktailsDetailsData.Success(cacheMapper.map(cocktailDetailsCache))
        } catch (e: Exception) {
            CocktailsDetailsData.Fail(e)
        }

        override suspend fun fetchCocktailDetailsFromNetwork(cocktailName: String) = try {
            val cocktailDetailsCloud = cloudDataSource.fetchCocktailDetails(cocktailName)
            val cocktailDetails = cloudMapper.map(cocktailDetailsCloud)
            cacheDataSource.clear(cocktailName)
            cacheDataSource.save(cocktailDetails)
            CocktailsDetailsData.Success(cocktailDetails)
        } catch (e: Exception) {
            CocktailsDetailsData.Fail(e)
        }
    }
}