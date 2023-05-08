package edu.fullerton.fz.culinarycompanion

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.squareup.picasso.Picasso
import retrofit2.*
import edu.fullerton.fz.culinarycompanion.api.*
import edu.fullerton.fz.culinarycompanion.db.Favorite
import edu.fullerton.fz.culinarycompanion.db.myDatabaseRepository
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val randomMealFragment = this.supportFragmentManager.findFragmentById(R.id.random_meal_frame_layout)
        if (randomMealFragment == null) {
            val fragment = RandomMealFragment()
            this.supportFragmentManager
                .beginTransaction()
                .add(R.id.random_meal_frame_layout, fragment)
                .commit()
        }

        val categoryListFragment = this.supportFragmentManager.findFragmentById(R.id.category_list_frame_layout)
        if (categoryListFragment == null) {
            val fragment = CategoryListFragment()
            this.supportFragmentManager
                .beginTransaction()
                .add(R.id.category_list_frame_layout, fragment)
                .commit()
        }





    }


    /*
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
     */

}