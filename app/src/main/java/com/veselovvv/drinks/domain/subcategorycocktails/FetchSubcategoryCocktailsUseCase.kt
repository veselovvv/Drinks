package com.veselovvv.drinks.domain.subcategorycocktails

import com.veselovvv.drinks.data.subcategorycocktails.SubcategoryCocktailsDataToDomainMapper
import com.veselovvv.drinks.data.subcategorycocktails.SubcategoryCocktailsRepository

class FetchSubcategoryCocktailsUseCase(
    private val repository: SubcategoryCocktailsRepository,
    private val mapper: SubcategoryCocktailsDataToDomainMapper
) {
    suspend fun execute(categoryKey: String, subcategoryName: String) =
        repository.fetchCocktails(categoryKey, subcategoryName).map(mapper)
}