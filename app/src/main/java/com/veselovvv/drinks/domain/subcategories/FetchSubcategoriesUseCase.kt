package com.veselovvv.drinks.domain.subcategories

import com.veselovvv.drinks.data.subcategories.SubcategoriesDataToDomainMapper
import com.veselovvv.drinks.data.subcategories.SubcategoriesRepository

class FetchSubcategoriesUseCase(
    private val repository: SubcategoriesRepository,
    private val mapper: SubcategoriesDataToDomainMapper
) {
    suspend fun execute(categoryKey: String) = repository.fetchSubcategories(categoryKey).map(mapper)
}