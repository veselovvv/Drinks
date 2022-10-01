package com.veselovvv.drinks.domain.subcategorycocktails

import com.veselovvv.drinks.data.subcategorycocktails.SubcategoryCocktailData
import com.veselovvv.drinks.data.subcategorycocktails.SubcategoryCocktailDataToDomainMapper
import com.veselovvv.drinks.data.subcategorycocktails.SubcategoryCocktailsDataToDomainMapper

class BaseSubcategoryCocktailsDataToDomainMapper(
    private val mapper: SubcategoryCocktailDataToDomainMapper
) : SubcategoryCocktailsDataToDomainMapper() {
    override fun map(data: List<SubcategoryCocktailData>) =
        SubcategoryCocktailsDomain.Success(data, mapper)

    override fun map(e: Exception) = SubcategoryCocktailsDomain.Fail(getErrorType(e))
}