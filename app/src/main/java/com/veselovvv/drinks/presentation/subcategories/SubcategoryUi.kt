package com.veselovvv.drinks.presentation.subcategories

import com.veselovvv.drinks.core.Mapper
import com.veselovvv.drinks.core.Object

sealed class SubcategoryUi : Object<Unit, SubcategoryUi.BaseMapper> {
    override fun map(mapper: BaseMapper) = Unit
    open fun open(subcategoryListener: SubcategoriesAdapter.SubcategoryListener) = Unit

    object Progress : SubcategoryUi()

    class Base(private val subcategory: String) : SubcategoryUi() {
        override fun map(mapper: BaseMapper) = mapper.map(subcategory)
        override fun open(subcategoryListener: SubcategoriesAdapter.SubcategoryListener) =
            subcategoryListener.showSubcategory(subcategory)
    }

    class Fail(private val message: String) : SubcategoryUi() {
        override fun map(mapper: BaseMapper) = mapper.map(message)
    }

    interface BaseMapper : Mapper {
        fun map(data: String)
    }
}