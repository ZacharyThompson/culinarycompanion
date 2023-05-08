package edu.fullerton.fz.culinarycompanion

import android.app.Application
import android.util.Log
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import edu.fullerton.fz.culinarycompanion.api.Meal
import edu.fullerton.fz.culinarycompanion.api.MealDBExecutor
import kotlinx.coroutines.launch

private const val LOG_TAG = "DetailTabViewModel"

class DetailTabViewModel() : ViewModel() {
    lateinit var mealLiveData: LiveData<Meal>

    fun setMeal(idMeal: Int) {
        mealLiveData = MealDBExecutor().fetchMealByID(idMeal)
    }

}