package com.veselovvv.drinks.domain.cocktails

import com.veselovvv.drinks.core.Object
import com.veselovvv.drinks.presentation.cocktails.CocktailUi

data class CocktailDomain(
    private val name: String,
    private val category: String,
    private val photoUrl: String
) : Object<CocktailUi, CocktailDomainToUiMapper> {
    override fun map(mapper: CocktailDomainToUiMapper) = mapper.map(name, category, photoUrl)
}