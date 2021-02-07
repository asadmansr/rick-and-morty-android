package com.android.richandmortyapp.ui.characters_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.android.richandmortyapp.R


class CharactersListFragment : Fragment() {

    private lateinit var viewModel: CharactersListViewModel
    private lateinit var viewModelFactory: CharactersListViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_characters_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModelFactory = CharactersListViewModelFactory()
        viewModel =
            ViewModelProvider(this, viewModelFactory).get(CharactersListViewModel::class.java)
        getData()
    }

    private fun getData() {
        viewModel.getItems()
    }
}
