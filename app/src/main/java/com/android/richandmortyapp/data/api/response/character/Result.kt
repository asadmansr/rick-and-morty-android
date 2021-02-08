package com.android.richandmortyapp.data.api.response.character


import com.android.richandmortyapp.data.api.response.character.Location
import com.android.richandmortyapp.data.api.response.character.Origin

data class Result(
    val created: String,
    val episode: List<String>,
    val gender: String,
    val id: Int,
    val image: String,
    val location: Location,
    val name: String,
    val origin: Origin,
    val species: String,
    val status: String,
    val type: String,
    val url: String
)
