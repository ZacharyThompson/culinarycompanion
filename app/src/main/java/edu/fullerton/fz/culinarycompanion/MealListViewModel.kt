package edu.fullerton.fz.culinarycompanion

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import edu.fullerton.fz.culinarycompanion.api.Meal
import edu.fullerton.fz.culinarycompanion.api.MealDBExecutor

private const val LOG_TAG = "MealListViewModel"
class MealListViewModel : ViewModel(){
    lateinit var mealLiveData: LiveData<List<Meal>>


    fun getMealsByCategory(strCategory: String): LiveData<List<Meal>> {
        mealLiveData = MealDBExecutor().fetchMealsByCategory(strCategory)
        return mealLiveData
    }
}
