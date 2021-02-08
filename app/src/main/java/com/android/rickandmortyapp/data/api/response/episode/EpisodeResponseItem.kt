package com.android.rickandmortyapp.data.api.response.episode


import com.google.gson.annotations.SerializedName

data class EpisodeResponseItem(
    @SerializedName("air_date")
    val airDate: String,
    val characters: List<String>,
    val created: String,
    val episode: String,
    val id: Int,
    val name: String,
    val url: String
)