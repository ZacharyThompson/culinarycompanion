package edu.fullerton.fz.culinarycompanion

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.squareup.picasso.Picasso
import edu.fullerton.fz.culinarycompanion.api.Meal

private const val LOG_TAG = "DetailTabActivity"
class DetailTabActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_tab)

        val idMeal = intent.getIntExtra("idMeal", 0)

        // Find views
        val mealThumbImageView: ImageView = findViewById(R.id.mealImageView)
        val mealNameTextView: TextView = findViewById(R.id.mealTextView)

        val detailTabViewModel = ViewModelProvider(this)[DetailTabViewModel::class.java]

        detailTabViewModel.setMeal(idMeal)
        detailTabViewModel.mealLiveData.observe(this) {meal->
            if (meal != null) {
                Log.d(LOG_TAG, "Meal Name: ${meal.strMeal}")
                // Populate views with meal data
                Picasso.get().load(meal.strMealThumb).into(mealThumbImageView)
                mealNameTextView.text = meal.strMeal
            }
        }
    }
}