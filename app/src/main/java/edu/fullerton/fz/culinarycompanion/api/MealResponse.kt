package edu.fullerton.fz.culinarycompanion.api

import com.google.gson.annotations.SerializedName

class MealResponse {
    @SerializedName("meals")
    lateinit var meals: List<Meal>
}
