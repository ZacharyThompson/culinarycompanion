package edu.fullerton.fz.culinarycompanion

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import edu.fullerton.fz.culinarycompanion.api.Category
import edu.fullerton.fz.culinarycompanion.api.MealDBExecutor

private const val LOG_TAG = "CategoryListViewModel"
class CategoryListViewModel : ViewModel(){
    val categoryLiveData: LiveData<List<Category>> = MealDBExecutor().fetchCategories()
}