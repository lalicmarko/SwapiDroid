package com.example.assessment.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.assessment.R
import com.example.assessment.adapters.StarWarsAdapter
import com.example.assessment.databinding.FragmentListBinding
import com.example.assessment.viewmodels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

/**
 * A fragment representing a list of StarWars items.
 *
 * @see StarWarsAdapter
 */
@AndroidEntryPoint
class ListFragment : Fragment() {

    private lateinit var starWarsAdapter: StarWarsAdapter
    private lateinit var binding: FragmentListBinding
    private val mainViewModel: MainViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        starWarsAdapter = StarWarsAdapter().apply {
            onItemClickedCallback = {
                mainViewModel.setSelectedEntity(it)
                findNavController().navigate(R.id.action_movieListFragment_to_mainFragment)
            }
        }

        mainViewModel.initialDataPull()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentListBinding.inflate(inflater, container, false)
        // Set the adapter
        binding.list.adapter = starWarsAdapter

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            mainViewModel.getData().collect {
                starWarsAdapter.submitList(it)
                binding.loadingProgress.visibility = GONE
            }
        }
    }
}