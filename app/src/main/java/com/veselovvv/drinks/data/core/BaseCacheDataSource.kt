package com.veselovvv.drinks.data.core

import android.content.Context
import androidx.room.Room

interface BaseCacheDataSource<D> {
    fun dao(): D

    abstract class Abstract<D>(
        context: Context, databaseName: String
    ) : BaseCacheDataSource<D> {
        protected val room = Room.databaseBuilder(
            context,
            CocktailDatabase::class.java,
            databaseName
        ).build()
    }
}