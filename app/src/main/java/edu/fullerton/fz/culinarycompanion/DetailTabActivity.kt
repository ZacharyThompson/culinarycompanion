package edu.fullerton.fz.culinarycompanion

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import edu.fullerton.fz.culinarycompanion.api.MealDBExecutor

class DetailTabActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_tab)

        // Fetch meal using api
        val idMeal = intent.getIntExtra("idMeal", 0)
        val mealLiveData = MealDBExecutor().fetchMealByID(idMeal)

        // Find views
        val mealThumbImageView: ImageView = findViewById(R.id.randMealImageView)
        val mealNameTextView: TextView = findViewById(R.id.randMealTextView)


        // Populate views with meal data
        if (mealLiveData.value != null) {
            Picasso.get().load(mealLiveData.value!!.strMealThumb).into(mealThumbImageView)
            mealNameTextView.text = mealLiveData.value!!.strMeal
        }
    }
}