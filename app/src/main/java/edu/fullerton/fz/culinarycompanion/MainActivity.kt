package edu.fullerton.fz.culinarycompanion

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.squareup.picasso.Picasso
import retrofit2.*
import edu.fullerton.fz.culinarycompanion.api.*
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    private lateinit var headerImage: ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        headerImage = findViewById(R.id.iv_meal)
        //here we call fetchMeals(random) to populate the headerimage
        this.fetchMeals()
        this.fetchFaves()
        this.postFave("100")
    }
    fun fetchMeals(): LiveData<List<Meal>> {
        val responseLiveData: MutableLiveData<List<Meal>> = MutableLiveData()
        val mealDBRequest: Call<MealResponse> = this.api.fetchMeals()
        mealDBRequest.enqueue(object: Callback<MealResponse> {
            override fun onFailure(call: Call<MealResponse>, t: Throwable) {
                Log.e("Main API", "Response received from MealDB fetch failed")
            }
            override fun onResponse(
                call: Call<MealResponse>,
                response: Response<MealResponse>
            ) {
                val MealResponse: MealResponse? = response.body()
                var myMeals: List<Meal>? = MealResponse?.meals
                responseLiveData.value = myMeals
                //here we actually access the data from the response and put it into the header
                Picasso.get().load(myMeals!![0].strMealThumb).into(headerImage)
            }
        })
        return responseLiveData
    }



    //here we are using the random endpoint.
    private val api: MealDBAPIRandom
    init {
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://www.themealdb.com/api/json/v1/1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        this.api = retrofit.create(MealDBAPIRandom::class.java)
    }
    private val Faveapi: FavoritesAPI
    init {
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("http://waggles.org:8080/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        this.Faveapi = retrofit.create(FavoritesAPI::class.java)
    }

    fun fetchFaves(): LiveData<List<String>> {

        val responseLiveData: MutableLiveData<List<String>> = MutableLiveData()

        val favoritesRequest: Call<favoritesResponse> = this.Faveapi.fetchFavorites()

        favoritesRequest.enqueue(object: Callback<favoritesResponse> {

            override fun onFailure(call: Call<favoritesResponse>, t: Throwable) {
                Log.e("API TEST", "Response received from FavoritesAPI fetch failed" + t.message)
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
                Log.d("Favorites test", favorites!![0])

            }
        })

        return responseLiveData
    }
    fun postFave(value:String): LiveData<String> {

        val responseLiveData: MutableLiveData<String> = MutableLiveData()

        val favoritesRequest: Call<favePostResponse> = this.Faveapi.postFavorite(value)

        favoritesRequest.enqueue(object: Callback<favePostResponse> {

            override fun onFailure(call: Call<favePostResponse>, t: Throwable) {
                Log.e("API TEST", "Response received from FavoritesAPI fetch failed" + t.message)
            }

            override fun onResponse(
                call: Call<favePostResponse>,
                response: Response<favePostResponse>
            ) {
                val favoritesResponse: favePostResponse? = response.body()
                Log.d("API TEST", "Success!")
                Log.d("API TEST", response.raw().toString())

                var favorites: String? = favoritesResponse?.fave
                //Log.d(TAG, "ImgFlip templates: $memeTemplates")
                responseLiveData.value = favorites
                Log.d("Favorites POST test", favorites!!)

            }
        })

        return responseLiveData
    }
}