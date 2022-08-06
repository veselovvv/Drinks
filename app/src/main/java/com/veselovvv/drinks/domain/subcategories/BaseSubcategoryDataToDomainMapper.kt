package com.veselovvv.drinks.domain.subcategories

import com.veselovvv.drinks.data.subcategories.SubcategoryDataToDomainMapper

class BaseSubcategoryDataToDomainMapper : SubcategoryDataToDomainMapper {
    override fun map(subcategory: String) = SubcategoryDomain(subcategory)
}