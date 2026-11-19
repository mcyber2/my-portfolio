package com.example.mohammedportfolio.ui.education

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
import com.example.mohammedportfolio.databinding.FragmentEducationBinding
import com.example.mohammedportfolio.viewmodel.EducationViewModel
import com.example.mohammedportfolio.viewmodel.ViewModelFactory
import kotlinx.coroutines.launch

class EducationFragment : Fragment() {

    private var _binding: FragmentEducationBinding? = null
    private val binding get() = _binding!!

    private val viewModel: EducationViewModel by viewModels {
        ViewModelFactory(requireContext())
    }

    private lateinit var adapter: EducationAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEducationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = EducationAdapter()
        binding.rvEducation.layoutManager = LinearLayoutManager(requireContext())
        binding.rvEducation.adapter = adapter

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.allEducation.collect { adapter.updateList(it) }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}