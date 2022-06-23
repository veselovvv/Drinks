package com.veselovvv.drinks.domain.cocktails

import com.veselovvv.drinks.data.cocktails.CocktailsDataToDomainMapper

class FetchCocktailsUseCase(
    private val cocktailsRepository: CocktailsRepository,
    private val mapper: CocktailsDataToDomainMapper
) {
    suspend fun execute() = cocktailsRepository.fetchCocktails().map(mapper)
}