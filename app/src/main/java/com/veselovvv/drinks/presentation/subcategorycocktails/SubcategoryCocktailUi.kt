package com.veselovvv.drinks.presentation.subcategorycocktails

import com.veselovvv.drinks.core.Mapper
import com.veselovvv.drinks.core.Object

sealed class SubcategoryCocktailUi : Object<Unit, SubcategoryCocktailUi.BaseMapper> {
    override fun map(mapper: BaseMapper) = Unit
    open fun open(cocktailListener: SubcategoryCocktailsAdapter.CocktailListener) = Unit

    object Progress : SubcategoryCocktailUi()

    class Base(
        private val id: String,
        private val name: String,
        private val photoUrl: String
    ) : SubcategoryCocktailUi() {
        override fun map(mapper: BaseMapper) = mapper.map(id, name, photoUrl)
        override fun open(cocktailListener: SubcategoryCocktailsAdapter.CocktailListener) =
            cocktailListener.showCocktail(name, photoUrl)
    }

    class Fail(private val message: String) : SubcategoryCocktailUi() {
        override fun map(mapper: BaseMapper) = mapper.map(message)
    }

    interface BaseMapper : Mapper {
        fun map(id: String, name: String, photoUrl: String)
        fun map(text: String)
    }
}