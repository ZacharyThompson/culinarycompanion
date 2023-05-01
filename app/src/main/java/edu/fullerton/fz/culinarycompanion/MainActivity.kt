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

}