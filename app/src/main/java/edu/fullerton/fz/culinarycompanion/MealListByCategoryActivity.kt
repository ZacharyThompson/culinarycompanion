package edu.fullerton.fz.culinarycompanion

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

private const val LOG_TAG = "MealListByCategoryActivity"
class MealListByCategoryActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_meal_list_by_category)

        val strCategory: String? = intent.getStringExtra("strCategory")

        if (strCategory != null) {
            Log.i(LOG_TAG, "Making list of meals in $strCategory category." )

            // Initialize meal list fragment
            val mealListFragment = this.supportFragmentManager.findFragmentById(R.id.meal_list_frame_layout)
            if (mealListFragment == null) {
                val fragment = MealListFragment(strCategory)
                this.supportFragmentManager
                    .beginTransaction()
                    .add(R.id.meal_list_frame_layout, fragment)
                    .commit()
            }

        }
        else {
            Log.e(LOG_TAG, "Category string not found.")
        }
    }
}
