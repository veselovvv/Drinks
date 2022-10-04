package com.veselovvv.drinks.domain.subcategories

import com.veselovvv.drinks.core.Category
import com.veselovvv.drinks.data.subcategories.SubcategoriesDataToDomainMapper
import com.veselovvv.drinks.data.subcategories.SubcategoriesRepository

class FetchSubcategoriesUseCase(
    private val repository: SubcategoriesRepository,
    private val mapper: SubcategoriesDataToDomainMapper
) {
    suspend fun execute(category: Category) = repository.fetchSubcategories(category).map(mapper)
}