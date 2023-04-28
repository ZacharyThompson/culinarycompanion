package edu.fullerton.fz.culinarycompanion.api

import retrofit2.Call
import retrofit2.http.GET

interface MealDBAPICategories {
    @GET("categories.php")
    fun fetchMeals(): Call<CategoryResponse>
}