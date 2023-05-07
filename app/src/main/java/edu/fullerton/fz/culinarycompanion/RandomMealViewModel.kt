package edu.fullerton.fz.culinarycompanion

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import edu.fullerton.fz.culinarycompanion.api.Meal
import edu.fullerton.fz.culinarycompanion.api.MealDBExecutor

private const val LOG_TAG = "RandomMealViewModel"
class RandomMealViewModel: ViewModel() {
    private val randMealLiveData: LiveData<List<Meal>> = MealDBExecutor().fetchRandomMeals()

    fun getRandMealLiveData(): LiveData<List<Meal>> {
        return randMealLiveData
    }

}