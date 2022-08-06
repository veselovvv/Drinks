package com.veselovvv.drinks.data.subcategories

import com.veselovvv.drinks.core.Mapper
import com.veselovvv.drinks.domain.subcategories.SubcategoryDomain

interface SubcategoryDataToDomainMapper : Mapper {
    fun map(subcategory: String): SubcategoryDomain
}