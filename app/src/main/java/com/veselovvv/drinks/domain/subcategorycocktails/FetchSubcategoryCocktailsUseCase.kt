package com.veselovvv.drinks.domain.subcategorycocktails

import com.veselovvv.drinks.core.Category
import com.veselovvv.drinks.data.subcategorycocktails.SubcategoryCocktailsDataToDomainMapper
import com.veselovvv.drinks.data.subcategorycocktails.SubcategoryCocktailsRepository

class FetchSubcategoryCocktailsUseCase(
    private val repository: SubcategoryCocktailsRepository,
    private val mapper: SubcategoryCocktailsDataToDomainMapper
) {
    suspend fun execute(category: Category, subcategoryName: String) =
        repository.fetchCocktails(category, subcategoryName).map(mapper)
}