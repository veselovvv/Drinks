package com.veselovvv.drinks.presentation.cocktails

import android.content.Context
import com.veselovvv.drinks.core.Read
import com.veselovvv.drinks.core.Save

interface CocktailCache : Save<Triple<String, String, String>>, Read<Triple<String, String, String>> {
    class Base(context: Context) : CocktailCache {
        private val sharedPreferences =
            context.getSharedPreferences(COCKTAIL_DATA_FILENAME, Context.MODE_PRIVATE)

        override fun read() = Triple(
            sharedPreferences.getString(COCKTAIL_NAME_KEY, "") ?: "",
            sharedPreferences.getString(COCKTAIL_CATEGORY_KEY, "") ?: "",
            sharedPreferences.getString(COCKTAIL_PHOTO_URL_KEY, "") ?: ""
        )

        override fun save(data: Triple<String, String, String>) =
            sharedPreferences.edit()
                .putString(COCKTAIL_NAME_KEY, data.first)
                .putString(COCKTAIL_CATEGORY_KEY, data.second)
                .putString(COCKTAIL_PHOTO_URL_KEY, data.third)
                .apply()

        private companion object {
            const val COCKTAIL_DATA_FILENAME = "cocktailData"
            const val COCKTAIL_NAME_KEY = "cocktailNameKey"
            const val COCKTAIL_CATEGORY_KEY = "cocktailCategoryKey"
            const val COCKTAIL_PHOTO_URL_KEY = "cocktailPhotoUrlKey"
        }
    }
}