package edu.fullerton.fz.culinarycompanion.api

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val TAG = "MealDBExecutor"


//www.themealdb.com/api/json/v1/1/search.php?s=Arrabiata
//returns object with an object that has a list of Meal Objects

//www.themealdb.com/api/json/v1/1/filter.php?i=chicken_breast
//returns object with list of Meal objects
class MealDBExecutor {
    private val api: MealDBAPI

    init {
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://www.themealdb.com/api/json/v1/1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        this.api = retrofit.create(MealDBAPI::class.java)
    }
    fun fetchMealbyID(): LiveData<List<Meal>> {

        val responseLiveData: MutableLiveData<List<Meal>> = MutableLiveData()

        val imgFlipRequest: Call<MealDBGetMealbyID> = this.api.fetchTemplate()

        imgFlipRequest.enqueue(object: Callback<MealDBGetMealbyID> {

            override fun onFailure(call: Call<MealDBGetMealbyID>, t: Throwable) {
                Log.e(TAG, "Response received from MealDB fetch failed")
            }

            override fun onResponse(
                call: Call<MealDBGetMealbyID>,
                response: Response<MealDBGetMealbyID>
            ) {
                //Log.d(TAG, "Response received from ImgFlip get_memes endpoint")

                val MealDBGetMealbyID: MealDBGetMealbyID? = response.body()
                val MealDBGetMealbyIDData: MealDBGetMealbyIDData? = MealDBGetMealbyID?.data

                var mealTemplates: List<Meal> = MealDBGetMealbyIDData?.Meals ?: mutableListOf()
                //Log.d(TAG, "ImgFlip templates: $memeTemplates")
                responseLiveData.value = mealTemplates
            }
        })

        return responseLiveData
    }
}