package com.veselovvv.drinks.data.subcategories

import com.veselovvv.drinks.core.Object
import com.veselovvv.drinks.domain.subcategories.SubcategoriesDomain

sealed class SubcategoriesData : Object<SubcategoriesDomain, SubcategoriesDataToDomainMapper> {
    data class Success(private val subcategories: List<SubcategoryData>) : SubcategoriesData() {
        override fun map(mapper: SubcategoriesDataToDomainMapper) = mapper.map(subcategories)
    }

    data class Fail(private val exception: Exception) : SubcategoriesData() {
        override fun map(mapper: SubcategoriesDataToDomainMapper) = mapper.map(exception)
    }
}