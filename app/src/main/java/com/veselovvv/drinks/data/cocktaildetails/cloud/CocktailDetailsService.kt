package com.veselovvv.drinks.data.cocktaildetails.cloud

import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Query

interface CocktailDetailsService {
    @GET("search.php")
    suspend fun fetchCocktailDetails(@Query("s") cocktailName: String): ResponseBody
}