package edu.fullerton.fz.culinarycompanion.api
import com.google.gson.annotations.SerializedName

data class favoritesResponse(
    @SerializedName("favorites" ) var favorites : ArrayList<String> = arrayListOf()
)
