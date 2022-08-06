package com.veselovvv.drinks.domain.subcategories

import com.veselovvv.drinks.core.ErrorType
import com.veselovvv.drinks.core.Object
import com.veselovvv.drinks.data.subcategories.SubcategoryData
import com.veselovvv.drinks.data.subcategories.SubcategoryDataToDomainMapper
import com.veselovvv.drinks.presentation.subcategories.SubcategoriesUi

sealed class SubcategoriesDomain : Object<SubcategoriesUi, SubcategoriesDomainToUiMapper> {
    data class Success(
        private val subcategories: List<SubcategoryData>,
        private val subcategoryMapper: SubcategoryDataToDomainMapper
    ) : SubcategoriesDomain() {
        override fun map(mapper: SubcategoriesDomainToUiMapper) = mapper.map(subcategories.map { subcategory ->
            subcategory.map(subcategoryMapper)
        })
    }

    data class Fail(private val errorType: ErrorType) : SubcategoriesDomain() {
        override fun map(mapper: SubcategoriesDomainToUiMapper) = mapper.map(errorType)
    }
}