package edu.fullerton.fz.culinarycompanion.api

import com.google.gson.annotations.SerializedName

class MealDBGetMealbyID {
    @SerializedName("data")
    lateinit var data: MealDBGetMealbyIDData
}