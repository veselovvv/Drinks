package com.veselovvv.drinks.data.subcategorycocktails.cloud

import com.veselovvv.drinks.core.Mapper
import com.veselovvv.drinks.data.subcategorycocktails.SubcategoryCocktailData
import com.veselovvv.drinks.data.subcategorycocktails.ToSubcategoryCocktailMapper

interface SubcategoryCocktailsCloudMapper :
    Mapper.Data<List<SubcategoryCocktailCloud>, List<SubcategoryCocktailData>> {

    class Base(
        private val toSubcategoryCocktailMapper: ToSubcategoryCocktailMapper
    ) : SubcategoryCocktailsCloudMapper {
        override fun map(data: List<SubcategoryCocktailCloud>) = data.map { subcategoryCocktailCloud ->
            subcategoryCocktailCloud.map(toSubcategoryCocktailMapper)
        }
    }
}