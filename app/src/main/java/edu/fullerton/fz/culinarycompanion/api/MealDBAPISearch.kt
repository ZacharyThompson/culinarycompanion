package edu.fullerton.fz.culinarycompanion.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MealDBAPISearch {
    @GET("search.php")
    fun fetchMeals(@Query("s") searchPhrase: String): Call<MealResponse>
}