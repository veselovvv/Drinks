package com.veselovvv.drinks.data.subcategories

import com.veselovvv.drinks.core.Object
import com.veselovvv.drinks.domain.subcategories.SubcategoryDomain

data class SubcategoryData(
    private val subcategory: String
) : Object<SubcategoryDomain, SubcategoryDataToDomainMapper> {
    override fun map(mapper: SubcategoryDataToDomainMapper) = mapper.map(subcategory)
}