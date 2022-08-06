package com.veselovvv.drinks.data.subcategories.cloud

import com.veselovvv.drinks.core.Mapper
import com.veselovvv.drinks.data.subcategories.SubcategoryData
import com.veselovvv.drinks.data.subcategories.ToSubcategoryMapper

interface SubcategoriesCloudMapper : Mapper.Data<List<SubcategoryCloud>, List<SubcategoryData>> {
    override fun map(data: List<SubcategoryCloud>): List<SubcategoryData>

    class Base(private val toSubcategoryMapper: ToSubcategoryMapper) : SubcategoriesCloudMapper {
        override fun map(data: List<SubcategoryCloud>) = data.map { subcategoryCloud ->
            subcategoryCloud.map(toSubcategoryMapper)
        }
    }
}