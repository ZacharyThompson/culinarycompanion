package edu.fullerton.fz.culinarycompanion

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import com.squareup.picasso.Picasso
import edu.fullerton.fz.culinarycompanion.api.Meal

class RandomMealFragment: Fragment() {
    private lateinit var randomMealViewModel: RandomMealViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_random_meal, container, false)

        // Find views
        val randMealNameText: TextView = view.findViewById(R.id.randMealTextView)
        val randMealCategoryText: TextView = view.findViewById(R.id.randMealCategoryTextView)
        val randMealAreaText: TextView = view.findViewById(R.id.randMealAreaTextView)
        val randMealImage: ImageView = view.findViewById(R.id.randMealImageView)

        randomMealViewModel.getRandMealLiveData().observe(viewLifecycleOwner) {randMealList ->
            randMealList?.let {
                val meal = it[0]
                // Populate views with data from random meal
                randMealNameText.text = meal.strMeal
                randMealCategoryText.text = meal.strCategory
                randMealAreaText.text = meal.strArea
                Picasso.get().load(meal.strMealThumb).into(randMealImage)
            }
        }

        randMealImage.setOnClickListener {
            val intent = Intent(activity, DetailTabActivity::class.java)
            startActivity(intent)
        }

        return view
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        randomMealViewModel = ViewModelProvider(this.requireActivity())[RandomMealViewModel::class.java]
    }

    companion object {
        fun newInstance(): RandomMealFragment {
            return RandomMealFragment()
        }
    }
}