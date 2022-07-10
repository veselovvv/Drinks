package com.veselovvv.drinks.data.randomcocktail.cloud

import okhttp3.ResponseBody
import retrofit2.http.GET

interface RandomCocktailService {
    @GET("random.php")
    suspend fun fetchRandomCocktail(): ResponseBody
}