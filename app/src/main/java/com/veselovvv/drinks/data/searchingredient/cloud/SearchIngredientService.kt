package com.veselovvv.drinks.data.searchingredient.cloud

import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchIngredientService {
    @GET("search.php")
    suspend fun fetchIngredient(@Query("i") name: String): ResponseBody
}