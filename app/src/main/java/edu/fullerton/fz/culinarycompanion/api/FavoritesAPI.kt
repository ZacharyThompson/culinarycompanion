package edu.fullerton.fz.culinarycompanion.api


import retrofit2.Call
import retrofit2.http.*

interface FavoritesAPI {
    @GET("favorites")
    fun fetchFavorites(): Call<favoritesResponse>

    @POST("favorites/{value}")
    fun postFavorite(@Path("value") value: String): Call<favePostResponse>
}