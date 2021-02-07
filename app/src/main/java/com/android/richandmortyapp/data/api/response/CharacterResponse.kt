package com.android.richandmortyapp.data.api.response


import com.google.gson.annotations.SerializedName

data class CharacterResponse(
    val info: Info,
    val results: List<Result>
)
