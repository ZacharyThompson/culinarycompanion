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
class MealDBExecutor {
    private val api: MealDBAPIRandom
    private val category_api: MealDBAPICategories

    init {
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://www.themealdb.com/api/json/v1/1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        this.api = retrofit.create(MealDBAPIRandom::class.java)
        this.category_api = retrofit.create(MealDBAPICategories::class.java)
    }
    fun fetchMeals(): LiveData<List<Meal>> {

        val responseLiveData: MutableLiveData<List<Meal>> = MutableLiveData()

        val mealDBRequest: Call<MealResponse> = this.api.fetchMeals()

        mealDBRequest.enqueue(object: Callback<MealResponse> {

            override fun onFailure(call: Call<MealResponse>, t: Throwable) {
                Log.e(TAG, "Response received from MealDB fetch failed")
            }

            override fun onResponse(
                call: Call<MealResponse>,
                response: Response<MealResponse>
            ) {
                val MealResponse: MealResponse? = response.body()
                Log.d("API TEST", "Success!")
                Log.d("API TEST", response.raw().toString())

                var myMeals: List<Meal>? = MealResponse?.meals
                //Log.d(TAG, "ImgFlip templates: $memeTemplates")
                responseLiveData.value = myMeals
                Log.d("API TEST", myMeals!![0].strInstructions!!)

            }
        })

        return responseLiveData
    }

    fun fetchCategories(): LiveData<List<Category>> {
        val responseLiveData: MutableLiveData<List<Category>> = MutableLiveData()

        val mealDBRequest: Call<CategoryResponse> = this.category_api.fetchCategories()

        mealDBRequest.enqueue(object: Callback<CategoryResponse> {

            override fun onFailure(call: Call<CategoryResponse>, t: Throwable) {
                Log.e(TAG, "Response received from CategoryDB fetch failed")
            }

            override fun onResponse(
                call: Call<CategoryResponse>,
                response: Response<CategoryResponse>
            ) {
                val CategoryResponse: CategoryResponse? = response.body()
                Log.d("API TEST", "Success!")
                Log.d("API TEST", response.raw().toString())

                var myCategorys: List<Category>? = CategoryResponse?.categories
                //Log.d(TAG, "ImgFlip templates: $memeTemplates")
                responseLiveData.value = myCategorys
                Log.d("API TEST", myCategorys!![2].strCategory!!)

            }
        })

        return responseLiveData

    }

}