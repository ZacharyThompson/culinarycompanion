package edu.fullerton.fz.culinarycompanion.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MealDBAPIbyCategory {
    @GET("filter.php")
    fun fetchMeals(@Query("c") category: String): Call<MealListResponse>
}