package edu.fullerton.fz.culinarycompanion.api
import com.google.gson.annotations.SerializedName

class MealDBGetMealbyIDData {
    @SerializedName("MealsbyID")
    lateinit var Meals: List<Meal>
}