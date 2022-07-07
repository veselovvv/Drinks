package com.veselovvv.drinks.domain.cocktaildetails

import com.veselovvv.drinks.data.cocktaildetails.CocktailDetailsRepository
import com.veselovvv.drinks.data.cocktaildetails.CocktailsDetailsDataToDomainMapper

class FetchCocktailDetailsFromNetworkUseCase(
    private val cocktailDetailsRepository: CocktailDetailsRepository,
    private val mapper: CocktailsDetailsDataToDomainMapper
) {
    suspend fun execute(cocktailName: String) =
        cocktailDetailsRepository.fetchCocktailDetailsFromNetwork(cocktailName).map(mapper)
}