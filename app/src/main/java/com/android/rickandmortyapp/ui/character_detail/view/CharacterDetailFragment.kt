package com.android.rickandmortyapp.ui.character_detail.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.rickandmortyapp.R
import com.android.rickandmortyapp.data.api.ApiService
import com.android.rickandmortyapp.data.api.response.character.Result
import com.android.rickandmortyapp.data.api.response.episode.EpisodeResponseItem
import com.android.rickandmortyapp.data.repository.character.CharacterRepository
import com.android.rickandmortyapp.data.repository.character.CharacterRepositoryImpl
import com.android.rickandmortyapp.data.repository.episode.EpisodeRepository
import com.android.rickandmortyapp.data.repository.episode.EpisodeRepositoryImpl
import com.android.rickandmortyapp.ui.character_detail.item.EpisodeItem
import com.android.rickandmortyapp.ui.character_detail.viewmodel.CharacterDetailViewModel
import com.android.rickandmortyapp.ui.character_detail.viewmodel.CharacterDetailViewModelFactory
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import kotlinx.android.synthetic.main.fragment_character_detail.*

class CharacterDetailFragment : Fragment() {

    private lateinit var viewModel: CharacterDetailViewModel
    private lateinit var viewModelFactory: CharacterDetailViewModelFactory
    private lateinit var characterRepository: CharacterRepository
    private lateinit var episodeRepository: EpisodeRepository
    private lateinit var apiService: ApiService

    private val args: CharacterDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_character_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        apiService = ApiService()
        characterRepository = CharacterRepositoryImpl(apiService)
        episodeRepository = EpisodeRepositoryImpl(apiService)
        viewModelFactory = CharacterDetailViewModelFactory(characterRepository, episodeRepository)
        viewModel =
            ViewModelProvider(this, viewModelFactory).get(CharacterDetailViewModel::class.java)

        getCharacter(args.id)

        toolbar.setNavigationOnClickListener {
            navigateBack()
        }
    }

    private fun getCharacter(id: Int) {
        Log.d("rmDebug", "making call")
        viewModel.getCharacterById(id)
        viewModel.character.observe(viewLifecycleOwner, Observer { character ->
            Log.d("rmDebug", "$character")

            if (character != null) {
                updateUI(character)
                val episodeQuery = parseEpisodes(character.episode)
                getEpisodes(episodeQuery)
            } else {
                showErrorToast()
            }
        })
    }

    private fun updateUI(character: Result) {
        character_detail_name.text = character.name
        character_detail_species.text = character.species
        character_detail_location.text = character.location.name
        character_detail_status.text = character.status
        Glide.with(character_detail_image)
            .load(character.image)
            .centerCrop()
            .placeholder(R.mipmap.ic_launcher_round)
            .diskCacheStrategy(DiskCacheStrategy.NONE)
            .skipMemoryCache(true)
            .into(character_detail_image)
    }

    private fun parseEpisodes(episodeList: List<String>): String {
        var episodes = ""
        episodeList.forEachIndexed { index, episode ->
            episodes += episode.split("/").last()
            if (index != episodeList.size - 1) {
                episodes += ","
            }
        }
        Log.d("rmDebug", "$episodes")
        return episodes
    }

    private fun getEpisodes(episodeQuery: String) {
        viewModel.getEpisodesOfCharacter(episodeQuery)
        viewModel.episodes.observe(viewLifecycleOwner, Observer { episodes ->
            Log.d("rmDebug", "$episodes")
            if (episodes != null) {
                initRecyclerView(episodes.toEpisodeItem())
            } else {
                showErrorToast()
            }
        })
    }

    private fun List<EpisodeResponseItem>.toEpisodeItem(): List<EpisodeItem> {
        return this.map {
            EpisodeItem(it)
        }
    }

    private fun initRecyclerView(items: List<EpisodeItem>) {
        val groupAdapter = GroupAdapter<GroupieViewHolder>().apply {
            addAll(items)
        }

        rv_episode_list.apply {
            layoutManager = LinearLayoutManager(this@CharacterDetailFragment.context)
            adapter = groupAdapter
        }
    }

    private fun showErrorToast() {
        Toast.makeText(requireContext(), "Something went wrong, try again later", Toast.LENGTH_LONG)
            .show()
    }

    private fun navigateBack() {
        requireView().findNavController().navigateUp()
    }
}
