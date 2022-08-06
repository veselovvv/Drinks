package com.veselovvv.drinks.data.subcategories

import com.veselovvv.drinks.core.Mapper

interface ToSubcategoryMapper : Mapper {
    fun map(subcategory: String): SubcategoryData

    class Base : ToSubcategoryMapper {
        override fun map(subcategory: String) = SubcategoryData(subcategory)
    }
}