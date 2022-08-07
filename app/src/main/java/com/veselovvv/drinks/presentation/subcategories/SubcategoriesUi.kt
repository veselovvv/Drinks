package com.veselovvv.drinks.presentation.subcategories

import com.veselovvv.drinks.core.Object
import com.veselovvv.drinks.domain.subcategories.SubcategoryDomain
import com.veselovvv.drinks.domain.subcategories.SubcategoryDomainToUiMapper

sealed class SubcategoriesUi : Object<Unit, SubcategoriesCommunication> {
    data class Success(
        private val subcategories: List<SubcategoryDomain>,
        private val subcategoryMapper: SubcategoryDomainToUiMapper
    ) : SubcategoriesUi() {
        override fun map(mapper: SubcategoriesCommunication) {
            val subcategoriesUi = subcategories.map { subcategory ->
                subcategory.map(subcategoryMapper)
            }
            mapper.map(subcategoriesUi)
        }
    }

    data class Fail(private val errorMessage: String) : SubcategoriesUi() {
        override fun map(mapper: SubcategoriesCommunication) =
            mapper.map(listOf(SubcategoryUi.Fail(errorMessage)))
    }
}