package edu.fullerton.fz.culinarycompanion.api

import com.google.gson.annotations.SerializedName

data class favePostResponse(
    @SerializedName("fave" ) var fave : String? = null
)
