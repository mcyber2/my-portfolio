package com.example.mohammedportfolio.ui.experience

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mohammedportfolio.databinding.FragmentExperienceBinding
import com.example.mohammedportfolio.viewmodel.ViewModelFactory
import com.example.mohammedportfolio.viewmodel.WorkExperienceViewModel
import kotlinx.coroutines.launch

class ExperienceFragment : Fragment() {

    private var _binding: FragmentExperienceBinding? = null
    private val binding get() = _binding!!

    private val viewModel: WorkExperienceViewModel by viewModels {
        ViewModelFactory(requireContext())
    }

    private lateinit var adapter: ExperienceAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentExperienceBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = ExperienceAdapter()
        binding.rvExperience.layoutManager = LinearLayoutManager(requireContext())
        binding.rvExperience.adapter = adapter

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.allExperience.collect { adapter.updateList(it) }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}