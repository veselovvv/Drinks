package com.veselovvv.drinks.domain.cocktails

import com.veselovvv.drinks.data.cocktails.CocktailsDataToDomainMapper

class SearchCocktailsUseCase(
    private val cocktailsRepository: CocktailsRepository,
    private val mapper: CocktailsDataToDomainMapper
) {
    suspend fun execute(query: String) = cocktailsRepository.searchCocktails(query).map(mapper)
}