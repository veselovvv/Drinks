package com.veselovvv.drinks.domain.subcategories

import com.veselovvv.drinks.core.Object
import com.veselovvv.drinks.presentation.subcategories.SubcategoryUi

data class SubcategoryDomain(
    private val subcategory: String
) : Object<SubcategoryUi, SubcategoryDomainToUiMapper> {
    override fun map(mapper: SubcategoryDomainToUiMapper) = mapper.map(subcategory)
}