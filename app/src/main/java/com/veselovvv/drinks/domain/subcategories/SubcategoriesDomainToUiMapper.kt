package com.veselovvv.drinks.domain.subcategories

import com.veselovvv.drinks.core.Mapper
import com.veselovvv.drinks.core.ResourceProvider
import com.veselovvv.drinks.presentation.subcategories.SubcategoriesUi

abstract class SubcategoriesDomainToUiMapper(resourceProvider: ResourceProvider) :
        Mapper.DomainToUi.Base<List<SubcategoryDomain>, SubcategoriesUi>(resourceProvider)