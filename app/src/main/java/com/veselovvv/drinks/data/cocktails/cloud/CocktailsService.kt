package com.veselovvv.drinks.data.cocktails.cloud

import okhttp3.ResponseBody
import retrofit2.http.GET

interface CocktailsService {
    @GET("search.php?f=a")
    suspend fun fetchCocktails(): ResponseBody
}