package com.example.assessment.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.assessment.R
import com.example.assessment.adapters.PeopleAdapter
import com.example.assessment.databinding.FragmentPeopleBinding
import com.example.assessment.viewmodels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

/**
 * A fragment representing a list of Items.
 */
@AndroidEntryPoint
class PeopleFragment : Fragment() {

    private lateinit var peopleAdapter: PeopleAdapter
    private lateinit var binding: FragmentPeopleBinding
    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        peopleAdapter = PeopleAdapter().apply {
            onItemClickedCallback = {
                findNavController().navigate(R.id.action_movieListFragment_to_mainFragment)
            }
        }

        mainViewModel.initialDataPull()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPeopleBinding.inflate(inflater, container, false)
        // Set the adapter
        binding.list.adapter = peopleAdapter

        mainViewModel.observeMovies().observe(viewLifecycleOwner, { movies ->
            peopleAdapter.submitList(movies ?: emptyList())
        })

        return binding.root
    }
}