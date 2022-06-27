package com.veselovvv.drinks.data.cocktaildetails

import com.veselovvv.drinks.data.cocktaildetails.cloud.CocktailDetailsCloudDataSource
import com.veselovvv.drinks.data.cocktaildetails.cloud.CocktailDetailsCloudMapper
import com.veselovvv.drinks.domain.cocktaildetails.CocktailDetailsRepository

class BaseCocktailDetailsRepository(
    private val cloudDataSource: CocktailDetailsCloudDataSource,
    private val cocktailsCloudMapper: CocktailDetailsCloudMapper
) : CocktailDetailsRepository {
    override suspend fun fetchCocktailDetails(cocktailName: String) = try {
        val cocktailDetailsCloud = cloudDataSource.fetchCocktailDetails(cocktailName)
        val cocktailDetails = cocktailsCloudMapper.map(cocktailDetailsCloud)
        CocktailsDetailsData.Success(cocktailDetails)
    } catch (e: Exception) {
        CocktailsDetailsData.Fail(e)
    }
}