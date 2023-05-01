package edu.fullerton.fz.culinarycompanion.api

import com.google.gson.annotations.SerializedName

data class ShortMeal(
    @SerializedName("strMeal"      ) var strMeal      : String? = null,
    @SerializedName("strMealThumb" ) var strMealThumb : String? = null,
    @SerializedName("idMeal"       ) var idMeal       : String? = null
)
