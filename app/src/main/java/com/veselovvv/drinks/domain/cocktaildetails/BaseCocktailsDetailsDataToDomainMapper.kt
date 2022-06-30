package com.veselovvv.drinks.domain.cocktaildetails

import com.veselovvv.drinks.data.cocktaildetails.CocktailDetailsData
import com.veselovvv.drinks.data.cocktaildetails.CocktailDetailsDataToDomainMapper
import com.veselovvv.drinks.data.cocktaildetails.CocktailsDetailsDataToDomainMapper

class BaseCocktailsDetailsDataToDomainMapper(
    private val cocktailDetailsMapper: CocktailDetailsDataToDomainMapper
) : CocktailsDetailsDataToDomainMapper() {
    override fun map(data: CocktailDetailsData) =
        CocktailsDetailsDomain.Success(data, cocktailDetailsMapper)
    override fun map(e: Exception) = CocktailsDetailsDomain.Fail(getErrorType(e))
}