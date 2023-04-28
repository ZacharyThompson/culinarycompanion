package edu.fullerton.fz.culinarycompanion.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MealDBAPIbyID {
    @GET("lookup.php")
    fun fetchMeals(@Query("i") id: Int): Call<MealResponse>
}