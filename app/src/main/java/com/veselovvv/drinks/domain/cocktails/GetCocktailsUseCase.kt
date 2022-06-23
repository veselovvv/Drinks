package com.veselovvv.drinks.domain.cocktails

class GetCocktailsUseCase(private val cocktailsRepository: CocktailsRepository) {
    fun execute() = cocktailsRepository.getCocktails()
}