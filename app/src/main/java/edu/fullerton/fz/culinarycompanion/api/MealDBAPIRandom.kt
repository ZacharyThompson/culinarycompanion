package edu.fullerton.fz.culinarycompanion.api
import retrofit2.Call
import retrofit2.http.GET

interface MealDBAPIRandom {
    @GET("random.php")
    fun fetchMeals(): Call<MealResponse>
}