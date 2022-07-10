package com.veselovvv.drinks.domain.randomcocktail

import com.veselovvv.drinks.data.randomcocktail.RandomCocktailRepository
import com.veselovvv.drinks.data.randomcocktail.RandomCocktailsDataToDomainMapper

class FetchRandomCocktailUseCase(
    private val repository: RandomCocktailRepository,
    private val mapper: RandomCocktailsDataToDomainMapper
) {
    suspend fun execute() = repository.fetchRandomCocktail().map(mapper)
}