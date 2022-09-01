package com.veselovvv.drinks.data.subcategorycocktails.cloud

import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Query

interface SubcategoryCocktailsService {
    @GET("filter.php")
    suspend fun fetchCategoriesSubcategoryCocktails(@Query("c") subcategoryName: String): ResponseBody

    @GET("filter.php")
    suspend fun fetchGlassSubcategoryCocktails(@Query("g") subcategoryName: String): ResponseBody

    @GET("filter.php")
    suspend fun fetchIngredientsSubcategoryCocktails(@Query("i") subcategoryName: String): ResponseBody

    @GET("filter.php")
    suspend fun fetchAlcoholicSubcategoryCocktails(@Query("a") subcategoryName: String): ResponseBody
}