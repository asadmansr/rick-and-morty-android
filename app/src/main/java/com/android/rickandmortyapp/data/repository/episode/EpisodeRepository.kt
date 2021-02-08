package com.android.rickandmortyapp.data.repository.episode

import com.android.rickandmortyapp.data.api.response.episode.EpisodeResponse
import retrofit2.Response

interface EpisodeRepository {

    suspend fun getEpisodesOfCharacter(episodeQuery: String): Response<EpisodeResponse>
}
