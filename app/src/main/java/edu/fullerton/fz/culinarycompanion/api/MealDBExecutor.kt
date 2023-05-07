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
    private val random_api: MealDBAPIRandom
    private val category_api: MealDBAPICategories
    private val meal_by_category_api: MealDBAPIbyCategory
    private val meal_by_id_api: MealDBAPIbyID

    init {
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://www.themealdb.com/api/json/v1/1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        this.random_api = retrofit.create(MealDBAPIRandom::class.java)
        this.category_api = retrofit.create(MealDBAPICategories::class.java)
        this.meal_by_category_api = retrofit.create(MealDBAPIbyCategory::class.java)
        this.meal_by_id_api = retrofit.create(MealDBAPIbyID::class.java)
    }

    fun fetchMealByID(idMeal: Int): LiveData<Meal> {
        val responseLiveData: MutableLiveData<Meal> = MutableLiveData()

        val mealDBRequest: Call<MealListResponse> = this.meal_by_id_api.fetchMeals(idMeal)

        mealDBRequest.enqueue(object: Callback<MealListResponse> {

            override fun onFailure(call: Call<MealListResponse>, t: Throwable) {
                Log.e(TAG, "Response received from MealDB fetch failed")
            }

            override fun onResponse(
                call: Call<MealListResponse>,
                response: Response<MealListResponse>
            ) {
                val MealListResponse: MealListResponse? = response.body()
                Log.d(TAG, "Success!")
                Log.d(TAG, response.raw().toString())

                if (MealListResponse != null) {
                    var myMeal: Meal = MealListResponse.meals[0]
                    responseLiveData.value = myMeal
                }
                else {
                    Log.e(TAG, "MealResponse in fetchMealByID is null")
                }

            }
        })

        return responseLiveData
    }
    fun fetchRandomMeals(): LiveData<List<Meal>> {
        val responseLiveData: MutableLiveData<List<Meal>> = MutableLiveData()

        val mealDBRequest: Call<MealListResponse> = this.random_api.fetchMeals()

        mealDBRequest.enqueue(object: Callback<MealListResponse> {

            override fun onFailure(call: Call<MealListResponse>, t: Throwable) {
                Log.e(TAG, "Response received from MealDB fetch failed")
            }

            override fun onResponse(
                call: Call<MealListResponse>,
                response: Response<MealListResponse>
            ) {
                val MealListResponse: MealListResponse? = response.body()
                Log.d(TAG, "Success! Random Meal fetched")
                Log.d(TAG, response.raw().toString())

                var myMeals: List<Meal>? = MealListResponse?.meals
                responseLiveData.value = myMeals
                Log.d(TAG, myMeals!![0].strInstructions!!)

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
                val categoryResponse: CategoryResponse? = response.body()
                Log.i(TAG, "Success!")
                Log.i(TAG, response.raw().toString())

                var myCategories: List<Category>? = categoryResponse?.categories
                responseLiveData.value = myCategories
                Log.d(TAG, "Category List size: ${myCategories!!.size}")

            }
        })

        return responseLiveData

    }

    fun fetchMealsByCategory(strCategory: String): LiveData<List<Meal>> {
        val responseLiveData: MutableLiveData<List<Meal>> = MutableLiveData()

        val mealDBRequest: Call<MealListResponse> = this.meal_by_category_api.fetchMeals(strCategory)

        mealDBRequest.enqueue(object: Callback<MealListResponse> {

            override fun onFailure(call: Call<MealListResponse>, t: Throwable) {
                Log.e(TAG, "Response received from MealDB fetch failed")
            }

            override fun onResponse(
                call: Call<MealListResponse>,
                response: Response<MealListResponse>
            ) {
                val mealListResponse: MealListResponse? = response.body()
                Log.i(TAG, "Success!")
                Log.i(TAG, response.raw().toString())

                var myMeals: List<Meal>? = mealListResponse?.meals
                responseLiveData.value = myMeals
                Log.d(TAG, "Meal List size: ${myMeals!!.size}")

            }
        })

        return responseLiveData

    }

}