package edu.fullerton.fz.culinarycompanion.api

import com.google.gson.annotations.SerializedName

class byCategoryResponse {

    @SerializedName("meals")
    lateinit var meals: List<ShortMeal>
}