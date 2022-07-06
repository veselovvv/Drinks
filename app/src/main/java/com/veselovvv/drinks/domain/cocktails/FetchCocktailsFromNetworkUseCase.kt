package com.veselovvv.drinks.domain.cocktails

import com.veselovvv.drinks.data.cocktails.CocktailsDataToDomainMapper
import com.veselovvv.drinks.data.cocktails.CocktailsRepository

class FetchCocktailsFromNetworkUseCase(
    private val cocktailsRepository: CocktailsRepository,
    private val mapper: CocktailsDataToDomainMapper
) {
    suspend fun execute() = cocktailsRepository.fetchCocktailsFromNetwork().map(mapper)
}