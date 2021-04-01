package com.example.assessment.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.example.assessment.R
import com.example.assessment.databinding.FragmentDetailsBinding
import com.example.assessment.model.ui.StarWarsEntityUI
import com.example.assessment.viewmodels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

/**
 * A fragment representing a details of selected StarWars item.
 */
@AndroidEntryPoint
class DetailsFragment : Fragment() {

    private lateinit var binding: FragmentDetailsBinding

    private val mainViewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewLifecycleOwner.lifecycleScope.launchWhenResumed {
            mainViewModel.getSelectedEntity().collect {
                bindSelectedEntity(it)
            }
        }
    }

    private fun bindSelectedEntity(entity: StarWarsEntityUI?) {
        binding.title.text = getString(R.string.details_title, entity?.getType())
        binding.description.text = entity?.getShortInfo() ?: "Selected entity is null"
    }
}