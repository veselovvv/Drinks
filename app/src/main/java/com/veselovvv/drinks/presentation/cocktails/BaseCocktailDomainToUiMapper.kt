package com.veselovvv.drinks.presentation.cocktails

import com.veselovvv.drinks.domain.cocktails.CocktailDomainToUiMapper

class BaseCocktailDomainToUiMapper : CocktailDomainToUiMapper {
    override fun map(id: String, name: String, category: String, photoUrl: String) =
        CocktailUi.Base(id, name, category, photoUrl)
}