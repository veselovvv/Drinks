package com.veselovvv.drinks.presentation.subcategories

import com.veselovvv.drinks.core.ErrorType
import com.veselovvv.drinks.core.ResourceProvider
import com.veselovvv.drinks.domain.subcategories.SubcategoriesDomainToUiMapper
import com.veselovvv.drinks.domain.subcategories.SubcategoryDomain
import com.veselovvv.drinks.domain.subcategories.SubcategoryDomainToUiMapper

class BaseSubcategoriesDomainToUiMapper(
    resourceProvider: ResourceProvider,
    private val subcategoryMapper: SubcategoryDomainToUiMapper
) : SubcategoriesDomainToUiMapper(resourceProvider) {
    override fun map(data: List<SubcategoryDomain>) = SubcategoriesUi.Success(data, subcategoryMapper)
    override fun map(errorType: ErrorType) = SubcategoriesUi.Fail(getErrorMessage(errorType))
}