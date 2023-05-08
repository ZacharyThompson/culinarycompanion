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
        val instructionsTextView: TextView = findViewById(R.id.instructionsTextView)
        val ingredientsListTextView: TextView = findViewById(R.id.ingredientsListTextVIew)

        val detailTabViewModel = ViewModelProvider(this)[DetailTabViewModel::class.java]

        detailTabViewModel.setMeal(idMeal)
        detailTabViewModel.mealLiveData.observe(this) {meal->
            if (meal != null) {
                Log.d(LOG_TAG, "Meal Name: ${meal.strMeal}")
                // Populate views with meal data
                Picasso.get().load(meal.strMealThumb).into(mealThumbImageView)
                mealNameTextView.text = meal.strMeal
                instructionsTextView.text = meal.strInstructions
                val ingredients = listOf(
                    meal.strIngredient1,
                    meal.strIngredient2,
                    meal.strIngredient3,
                    meal.strIngredient4,
                    meal.strIngredient5,
                    meal.strIngredient6,
                    meal.strIngredient7,
                    meal.strIngredient8,
                    meal.strIngredient9,
                    meal.strIngredient10,
                    meal.strIngredient11,
                    meal.strIngredient12,
                    meal.strIngredient13,
                    meal.strIngredient14,
                    meal.strIngredient15,
                    meal.strIngredient16,
                    meal.strIngredient17,
                    meal.strIngredient18,
                    meal.strIngredient19,
                    meal.strIngredient20,
                )
                for (ingredient in ingredients) {
                    ingredientsListTextView.append(ingredient + "\n")
                }
            }
        }
    }
}