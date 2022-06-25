package com.veselovvv.drinks.data.cocktails.cloud

import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Query

interface CocktailsService {
    @GET("search.php")
    suspend fun fetchCocktails(@Query("f") firstLetter: String): ResponseBody
}