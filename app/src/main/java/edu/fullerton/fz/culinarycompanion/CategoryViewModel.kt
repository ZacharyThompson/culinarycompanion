package edu.fullerton.fz.culinarycompanion

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import edu.fullerton.fz.culinarycompanion.api.Category
import edu.fullerton.fz.culinarycompanion.api.MealDBExecutor

private const val LOG_TAG = "CategoryViewModel"
class CategoryViewModel : ViewModel(){
    private val categoryLiveData: LiveData<List<Category>> = MealDBExecutor().fetchCategories()

    fun getCategories(): List<List<Category>>? {
        Log.i(LOG_TAG, "Getting Categories")
        Thread.sleep(5000)
        if (this.categoryLiveData.value != null) {
            Log.d(LOG_TAG, "Category List Size: ${categoryLiveData!!.value!!.size}")
            val data = categoryLiveData.value!!.chunked(2) {it.take(2)}
            return data
        }
        else {
            return null
        }
    }
}