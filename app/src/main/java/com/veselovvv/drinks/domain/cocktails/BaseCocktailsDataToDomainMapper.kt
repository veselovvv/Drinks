package com.veselovvv.drinks.domain.cocktails

import com.veselovvv.drinks.data.cocktails.CocktailData
import com.veselovvv.drinks.data.cocktails.CocktailDataToDomainMapper
import com.veselovvv.drinks.data.cocktails.CocktailsDataToDomainMapper

class BaseCocktailsDataToDomainMapper(
    private val cocktailMapper: CocktailDataToDomainMapper
) : CocktailsDataToDomainMapper() {
    override fun map(data: List<CocktailData>) = CocktailsDomain.Success(data, cocktailMapper)
    override fun map(e: Exception) = CocktailsDomain.Fail(getErrorType(e))
}