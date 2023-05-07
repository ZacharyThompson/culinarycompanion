package edu.fullerton.fz.culinarycompanion

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import edu.fullerton.fz.culinarycompanion.api.Category
import edu.fullerton.fz.culinarycompanion.api.Meal

private const val LOG_TAG = "MealListFragment"

val EMPTY_MEAL_LIST = listOf<List<Meal>>()

class MealListFragment(strCategory: String): Fragment() {
    private lateinit var mealRecyclerView: RecyclerView
    private lateinit var mealListViewModel: MealListViewModel
    private var parentCategoryName: String = strCategory

    private var adapter: MealAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_meals_list, container, false)
        this.mealRecyclerView = view.findViewById(R.id.meal_recycler_view) as RecyclerView
        this.mealRecyclerView.layoutManager = LinearLayoutManager(context)

        Log.d(LOG_TAG, "Create adapter with empty list")
        adapter = MealAdapter(EMPTY_MEAL_LIST)
        this.mealRecyclerView.adapter = adapter

        mealListViewModel.mealLiveData.observe(viewLifecycleOwner) { meal_list ->
            if (meal_list != null) {
                adapter = MealAdapter(meal_list.chunked(2) {it.take(2)})
                mealRecyclerView.adapter = adapter
            }
        }
        return view
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.mealListViewModel = ViewModelProvider(this.requireActivity())[MealListViewModel::class.java]
        this.mealListViewModel.getMealsByCategory(parentCategoryName)
    }

    private inner class MealHolder(view: View) : RecyclerView.ViewHolder(view) {
        private lateinit var meal1: Meal
        private lateinit var meal2: Meal

        private val meal1ImageView: ImageView = this.itemView.findViewById(R.id.listitem1_image_view)
        private val meal2ImageView: ImageView = this.itemView.findViewById(R.id.listitem2_image_view)
        private val meal1NameText: TextView = this.itemView.findViewById(R.id.listitem1_name_text_view)
        private val meal2NameText: TextView = this.itemView.findViewById(R.id.listitem2_name_text_view)

        fun bind(meal1: Meal, meal2: Meal) {
            this.meal1 = meal1
            this.meal2 = meal2
            this.meal1NameText.text = this.meal1.strMeal
            this.meal2NameText.text = this.meal2.strMeal
            Log.d(LOG_TAG, "Category1 Name loaded: ${meal1.strMeal}")
            Log.d(LOG_TAG, "Category2 Name loaded: ${meal2.strMeal}")
            Picasso.get().load(meal1.strMealThumb).into(meal1ImageView)
            Picasso.get().load(meal2.strMealThumb).into(meal2ImageView)
            Log.d(LOG_TAG, "Category1 Thumbnail loaded: ${meal1.strMealThumb}")
            Log.d(LOG_TAG, "Category2 Thumbnail loaded: ${meal2.strMealThumb}")

            meal1ImageView.setOnClickListener {

            }

            meal2ImageView.setOnClickListener {

            }
        }
    }
    private inner class MealAdapter (var meals: List<List<Meal>>)
        : RecyclerView.Adapter<MealHolder>()
    {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MealHolder {
            val view = layoutInflater.inflate(R.layout.list_item, parent, false)
            return MealHolder(view)
        }

        override fun onBindViewHolder(holder: MealHolder, position: Int) {
            val meal1 = this.meals[position][0]
            val meal2 = this.meals[position][1]
            holder.bind(meal1, meal2)
        }

        override fun getItemCount() = meals.size
    }

    companion object {
        fun newInstance(strCategory: String): MealListFragment {
            return MealListFragment(strCategory)
        }
    }
}