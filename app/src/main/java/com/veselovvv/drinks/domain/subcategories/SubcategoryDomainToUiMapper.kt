package com.veselovvv.drinks.domain.subcategories

import com.veselovvv.drinks.core.Mapper
import com.veselovvv.drinks.presentation.subcategories.SubcategoryUi

interface SubcategoryDomainToUiMapper : Mapper {
    fun map(subcategory: String): SubcategoryUi
}