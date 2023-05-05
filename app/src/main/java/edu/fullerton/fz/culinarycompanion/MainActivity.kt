package edu.fullerton.fz.culinarycompanion

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.squareup.picasso.Picasso
import retrofit2.*
import edu.fullerton.fz.culinarycompanion.api.*
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    private lateinit var headerImage: ImageView
    private lateinit var categoryImage1: ImageView
    private lateinit var categoryImage2: ImageView
    private lateinit var categoryTXT1: TextView
    private lateinit var categoryTXT2: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        headerImage = findViewById(R.id.iv_meal)
        categoryImage1 = findViewById(R.id.categoryimg1)
        categoryImage2 = findViewById(R.id.categoryimg2)
        categoryTXT1 = findViewById(R.id.category2text)
        categoryTXT2 = findViewById(R.id.category1text)
        //here we call fetchMeals(random) to populate the headerimage
        this.fetchMeals()
        this.fetchFaves()
        this.postFave("100")
        this.fetchCategories()
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

    fun fetchCategories(): LiveData<List<Category>> {
        val responseLiveData: MutableLiveData<List<Category>> = MutableLiveData()
        val mealDBRequest: Call<CategoryResponse> = this.categoryapi.fetchCategories()
        mealDBRequest.enqueue(object: Callback<CategoryResponse> {
            override fun onFailure(call: Call<CategoryResponse>, t: Throwable) {
                Log.e("Main API", "Response received from MealDB fetch failed")
            }
            override fun onResponse(
                call: Call<CategoryResponse>,
                response: Response<CategoryResponse>
            ) {
                val CategoryResponse: CategoryResponse? = response.body()
                var myCategories: List<Category>? = CategoryResponse?.categories
                responseLiveData.value = myCategories
                //here we actually access the data from the response and put it into the header
                Picasso.get().load(myCategories!![0].strCategoryThumb).into(categoryImage1)
                Picasso.get().load(myCategories!![1].strCategoryThumb).into(categoryImage2)
                categoryTXT1.setText(myCategories!![0].strCategory)
                categoryTXT2.setText(myCategories!![1].strCategory)
                Log.e("strcategory", myCategories!![1].strCategory + myCategories!![0].strCategory)
            }
        })
        return responseLiveData
    }



    //here we are using the random endpoint.
    private val categoryapi: MealDBAPICategories
    init {
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://www.themealdb.com/api/json/v1/1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        this.categoryapi = retrofit.create(MealDBAPICategories::class.java)
    }
}