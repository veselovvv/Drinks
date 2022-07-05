package com.veselovvv.drinks.data.cocktails.cache

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [ CocktailDb::class ], version = 1)
abstract class CocktailDatabase : RoomDatabase() {
    abstract fun cocktailDao(): CocktailDao
}