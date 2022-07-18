package com.veselovvv.drinks.domain.searchingredient

import com.veselovvv.drinks.data.searchingredient.IngredientRepository
import com.veselovvv.drinks.data.searchingredient.IngredientsDataToDomainMapper

class FetchIngredientUseCase(
    private val repository: IngredientRepository,
    private val mapper: IngredientsDataToDomainMapper
) {
    suspend fun execute(name: String) = repository.fetchIngredient(name).map(mapper)
}