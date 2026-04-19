package com.example.fhubo.Main

import com.google.gson.annotations.SerializedName

data class Main(
    @SerializedName("name") val name: String,
    @SerializedName("imagePath") val imagePath: String,
    @SerializedName("category") val category: String,
    @SerializedName("year") val year: Int,
    @SerializedName("id") val id: Long = 0,
    @SerializedName("ultimAcces") val ultimAcces: String? = null
)
