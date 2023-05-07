package edu.fullerton.fz.culinarycompanion

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import edu.fullerton.fz.culinarycompanion.api.Category
import edu.fullerton.fz.culinarycompanion.api.MealDBExecutor

private const val LOG_TAG = "CategoryViewModel"
class CategoryViewModel : ViewModel(){
    val categoryLiveData: LiveData<List<Category>> = MealDBExecutor().fetchCategories()
}