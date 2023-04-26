package edu.fullerton.fz.culinarycompanion.api
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface MealDBAPI {
    @GET("random.php")
    fun fetchMeals(): Call<MealResponse>
}