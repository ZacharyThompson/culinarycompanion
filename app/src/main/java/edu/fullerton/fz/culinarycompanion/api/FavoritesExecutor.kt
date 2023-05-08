package edu.fullerton.fz.culinarycompanion.api

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
private const val TAG = "FavoritesExecutor"
class FavoritesExecutor {
    private val api: FavoritesAPI

    init {
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("http://localhost:8080/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        this.api = retrofit.create(FavoritesAPI::class.java)
    }
    fun fetchFavorites(): LiveData<List<String>> {

        val responseLiveData: MutableLiveData<List<String>> = MutableLiveData()

        val favoritesRequest: Call<favoritesResponse> = this.api.fetchFavorites()

        favoritesRequest.enqueue(object: Callback<favoritesResponse> {

            override fun onFailure(call: Call<favoritesResponse>, t: Throwable) {
                Log.e(TAG, "Response received from FavoritesAPI fetch failed")
            }

            override fun onResponse(
                call: Call<favoritesResponse>,
                response: Response<favoritesResponse>
            ) {
                val favoritesResponse: favoritesResponse? = response.body()
                Log.d("API TEST", "Success!")
                Log.d("API TEST", response.raw().toString())

                var favorites: List<String>? = favoritesResponse?.favorites
                //Log.d(TAG, "ImgFlip templates: $memeTemplates")
                responseLiveData.value = favorites
                Log.d("API TEST", favorites!![0])

            }
        })

        return responseLiveData
    }

}