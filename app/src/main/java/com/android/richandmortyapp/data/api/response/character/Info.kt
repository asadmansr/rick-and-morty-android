package com.android.richandmortyapp.data.api.response.character


import com.google.gson.annotations.SerializedName

data class Info(
    val count: Int,
    val next: String,
    val pages: Int,
    val prev: Any
)
