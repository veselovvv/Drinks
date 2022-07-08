package com.veselovvv.drinks.domain.cocktaildetails

import com.veselovvv.drinks.data.cocktaildetails.CocktailDetailsRepository
import com.veselovvv.drinks.data.cocktaildetails.CocktailsDetailsDataToDomainMapper

class FetchCocktailDetailsFromNetworkUseCase(
    private val repository: CocktailDetailsRepository,
    private val mapper: CocktailsDetailsDataToDomainMapper
) {
    suspend fun execute(cocktailName: String) =
        repository.fetchCocktailDetailsFromNetwork(cocktailName).map(mapper)
}