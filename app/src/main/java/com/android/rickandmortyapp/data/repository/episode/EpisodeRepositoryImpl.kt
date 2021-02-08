package com.android.rickandmortyapp.data.repository.episode

import com.android.rickandmortyapp.data.api.ApiService
import com.android.rickandmortyapp.data.api.response.episode.EpisodeResponse
import retrofit2.Response

class EpisodeRepositoryImpl(
    private val apiService: ApiService
) : EpisodeRepository {

    override suspend fun getEpisodesOfCharacter(episodeQuery: String): Response<EpisodeResponse> =
        apiService.getEpisodes(episodeQuery)
}
