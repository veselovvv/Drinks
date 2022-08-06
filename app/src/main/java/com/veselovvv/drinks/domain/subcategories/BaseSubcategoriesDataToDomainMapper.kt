package com.veselovvv.drinks.domain.subcategories

import com.veselovvv.drinks.data.subcategories.SubcategoriesDataToDomainMapper
import com.veselovvv.drinks.data.subcategories.SubcategoryData
import com.veselovvv.drinks.data.subcategories.SubcategoryDataToDomainMapper

class BaseSubcategoriesDataToDomainMapper(
    private val subcategoryMapper: SubcategoryDataToDomainMapper
) : SubcategoriesDataToDomainMapper() {
    override fun map(data: List<SubcategoryData>) = SubcategoriesDomain.Success(data, subcategoryMapper)
    override fun map(e: Exception) = SubcategoriesDomain.Fail(getErrorType(e))
}