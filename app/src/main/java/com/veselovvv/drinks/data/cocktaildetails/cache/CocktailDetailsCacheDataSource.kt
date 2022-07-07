package com.veselovvv.drinks.data.cocktaildetails.cache

import android.content.Context
import com.veselovvv.drinks.data.cocktaildetails.CocktailDetailsData
import com.veselovvv.drinks.data.core.BaseCacheDataSource

interface CocktailDetailsCacheDataSource {
    fun save(cocktailDetails: CocktailDetailsData)
    fun read(name: String): CocktailDetailsDb?
    fun clear(name: String)

    class Base(
        context: Context,
        private val mapper: CocktailDetailsDataToDbMapper
    ) : CocktailDetailsCacheDataSource, BaseCacheDataSource.Abstract<CocktailDetailsDao>(
        context, "cocktail-database"
    ) {
        override fun dao() = room.cocktailDetailsDao()

        override fun save(cocktailDetails: CocktailDetailsData) =
            dao().addCocktailDetails(cocktailDetails.mapWith(mapper))

        override fun read(name: String) = dao().getCocktailDetails(name)
        override fun clear(name: String) = dao().deleteCocktailDetails(name)
    }
}