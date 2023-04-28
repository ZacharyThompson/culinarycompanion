package edu.fullerton.fz.culinarycompanion.api

import com.google.gson.annotations.SerializedName

class CategoryResponse {
    @SerializedName("categories")
    lateinit var categories: List<Category>
}