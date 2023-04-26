package edu.fullerton.fz.culinarycompanion

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import edu.fullerton.fz.culinarycompanion.api.MealDBAPI
import edu.fullerton.fz.culinarycompanion.api.MealResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.io.IOException

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /* Creates an instance of the UserService using a simple Retrofit builder using Moshi
     * as a JSON converter, this will append the endpoints set on the UserService interface
     * (for example '/api', '/api?results=2') with the base URL set here, resulting on the
     * full URL that will be called: 'https://randomuser.me/api' */
        val service = Retrofit.Builder()
            .baseUrl("https://www.themealdb.com/api/json/v1/1/")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(MealDBAPI::class.java)

        /* Calls the endpoint set on getUsers (/api) from UserService using enqueue method
         * that creates a new worker thread to make the HTTP call */
        service.fetchMeals().enqueue(object : Callback<MealResponse> {

            /* The HTTP call failed. This method is run on the main thread */
            override fun onFailure(call: Call<MealResponse>, t: Throwable) {
                Log.d("APIRESPONSE", "An error happened!")
                t.printStackTrace()
            }

            /* The HTTP call was successful, we should still check status code and response body
             * on a production app. This method is run on the main thread */
            override fun onResponse(call: Call<MealResponse>, response: Response<MealResponse>) {
                /* This will print the response of the network call to the Logcat */
                Log.d("APIRESPONSE", response.body()!!.results[0].strIngredient1!!)
            }
        })
    }
}