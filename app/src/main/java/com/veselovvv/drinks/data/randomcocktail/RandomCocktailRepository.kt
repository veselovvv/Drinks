package com.veselovvv.drinks.data.randomcocktail

import com.veselovvv.drinks.data.randomcocktail.cloud.RandomCocktailCloudDataSource
import com.veselovvv.drinks.data.randomcocktail.cloud.RandomCocktailCloudMapper

interface RandomCocktailRepository {
    suspend fun fetchRandomCocktail(): RandomCocktailsData

    class Base(
        private val cloudDataSource: RandomCocktailCloudDataSource,
        private val cloudMapper: RandomCocktailCloudMapper
    ) : RandomCocktailRepository {
        override suspend fun fetchRandomCocktail() = try {
            val randomCocktailCloud = cloudDataSource.fetchRandomCocktail()
            val randomCocktail = cloudMapper.map(randomCocktailCloud)
            RandomCocktailsData.Success(randomCocktail)
        } catch (e: Exception) {
            RandomCocktailsData.Fail(e)
        }
    }
}