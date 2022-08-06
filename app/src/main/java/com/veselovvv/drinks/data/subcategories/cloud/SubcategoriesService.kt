package com.veselovvv.drinks.data.subcategories.cloud

import okhttp3.ResponseBody
import retrofit2.http.GET

interface SubcategoriesService {
    @GET("list.php?c=list")
    suspend fun fetchCategoriesSubcategories(): ResponseBody

    @GET("list.php?g=list")
    suspend fun fetchGlassSubcategories(): ResponseBody

    @GET("list.php?i=list")
    suspend fun fetchIngredientsSubcategories(): ResponseBody

    @GET("list.php?a=list")
    suspend fun fetchAlcoholicSubcategories(): ResponseBody
}