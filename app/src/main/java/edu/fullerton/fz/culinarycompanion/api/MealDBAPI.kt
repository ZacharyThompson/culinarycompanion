package edu.fullerton.fz.culinarycompanion.api
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface MealDBAPI {
    @GET("search.php?f=a")
    fun fetchMeals(): Call<MealResponse>
}