package com.veselovvv.drinks.data.core

import androidx.room.Database
import androidx.room.RoomDatabase
import com.veselovvv.drinks.data.cocktaildetails.cache.CocktailDetailsDao
import com.veselovvv.drinks.data.cocktaildetails.cache.CocktailDetailsDb
import com.veselovvv.drinks.data.cocktails.cache.CocktailDao
import com.veselovvv.drinks.data.cocktails.cache.CocktailDb

@Database(entities = [ CocktailDb::class, CocktailDetailsDb::class ], version = 1)
abstract class CocktailDatabase : RoomDatabase() {
    abstract fun cocktailDao(): CocktailDao
    abstract fun cocktailDetailsDao(): CocktailDetailsDao
}