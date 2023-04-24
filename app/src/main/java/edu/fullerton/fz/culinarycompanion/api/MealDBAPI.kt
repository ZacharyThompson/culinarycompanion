package edu.fullerton.fz.culinarycompanion.api
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface MealDBAPI {
    @GET("lookup.php?i=52772")
    fun fetchTemplate(): Call<MealDBGetMealbyID>
}