package edu.fullerton.fz.culinarycompanion

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import edu.fullerton.fz.culinarycompanion.api.Meal
import edu.fullerton.fz.culinarycompanion.api.MealDBExecutor

class MealViewModel: ViewModel() {
    private var mealIndex: Int = 0

    val mealLiveData: LiveData<List<Meal>> = MealDBExecutor().fetchMeals()

    fun getTemplateIndex(): Int {
        return this.mealIndex
    }
    fun decreaseTemplateIndex() {
        if (mealLiveData.value != null) {
            mealIndex -= 1
            if (mealIndex < 0) {
                mealIndex = mealLiveData.value!!.size - 1
            }
        }
    }
    fun increaseTemplateIndex() {
        if (mealLiveData.value != null) {
            mealIndex += 1
            if (mealIndex >= mealLiveData.value!!.size) {
                mealIndex = 0
            }
        }
    }

    fun getCurrentMeal(): Meal? {
        if (this.mealLiveData.value != null) {
            if (this.mealIndex >= 0 && this.mealIndex <= this.mealLiveData.value!!.size) {
                return this.mealLiveData.value!![this.mealIndex]
            }
        }
        return null
    }
}