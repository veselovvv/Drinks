package com.veselovvv.drinks.data.subcategories

import com.veselovvv.drinks.core.Mapper
import com.veselovvv.drinks.domain.subcategories.SubcategoriesDomain

abstract class SubcategoriesDataToDomainMapper :
    Mapper.DataToDomain.Base<List<SubcategoryData>, SubcategoriesDomain>()