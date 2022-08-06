package com.veselovvv.drinks.presentation.subcategories

import com.veselovvv.drinks.domain.subcategories.SubcategoryDomainToUiMapper

class BaseSubcategoryDomainToUiMapper : SubcategoryDomainToUiMapper {
    override fun map(subcategory: String) = SubcategoryUi.Base(subcategory)
}