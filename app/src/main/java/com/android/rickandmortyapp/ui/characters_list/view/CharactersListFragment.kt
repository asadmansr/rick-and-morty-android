package com.android.rickandmortyapp.ui.characters_list.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.rickandmortyapp.R
import com.android.rickandmortyapp.data.api.ApiService
import com.android.rickandmortyapp.data.api.response.character.Result
import com.android.rickandmortyapp.data.repository.character.CharacterRepository
import com.android.rickandmortyapp.data.repository.character.CharacterRepositoryImpl
import com.android.rickandmortyapp.ui.characters_list.item.CharacterItem
import com.android.rickandmortyapp.ui.characters_list.viewmodel.CharactersListViewModel
import com.android.rickandmortyapp.ui.characters_list.viewmodel.CharactersListViewModelFactory
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import kotlinx.android.synthetic.main.fragment_characters_list.*


class CharactersListFragment : Fragment() {

    private lateinit var viewModel: CharactersListViewModel
    private lateinit var viewModelFactory: CharactersListViewModelFactory
    private lateinit var characterRepository: CharacterRepository
    private lateinit var apiService: ApiService

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_characters_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        apiService = ApiService()
        characterRepository = CharacterRepositoryImpl(apiService)
        viewModelFactory = CharactersListViewModelFactory(characterRepository)
        viewModel =
            ViewModelProvider(this, viewModelFactory).get(CharactersListViewModel::class.java)

        getData()
    }

    private fun getData() {
        viewModel.getCharacters()
        viewModel.characters.observe(viewLifecycleOwner, Observer { characters ->
            progress_characters_list.visibility = View.GONE
            if (characters == null) {
                tv_error_message.visibility = View.VISIBLE
            } else {
                initRecyclerView(characters.toCharacterItem())
            }
        })
    }

    private fun List<Result>.toCharacterItem(): List<CharacterItem> {
        return this.map {
            CharacterItem(it)
        }
    }

    private fun initRecyclerView(items: List<CharacterItem>) {
        val groupAdapter = GroupAdapter<GroupieViewHolder>().apply {
            addAll(items)
        }

        rv_characters_list.apply {
            layoutManager = LinearLayoutManager(this@CharactersListFragment.context)
            adapter = groupAdapter
        }

        groupAdapter.setOnItemClickListener { item, view ->
            val id = (item as CharacterItem).resultItem.id
            val action =
                CharactersListFragmentDirections.actionCharactersListFragmentToCharacterDetailFragment(
                    id
                )
            requireView().findNavController().navigate(action)
        }
    }
}
