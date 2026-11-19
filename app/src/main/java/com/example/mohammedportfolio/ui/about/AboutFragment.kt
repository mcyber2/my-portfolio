package com.example.mohammedportfolio.ui.about

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.mohammedportfolio.databinding.FragmentAboutBinding
import com.example.mohammedportfolio.viewmodel.PersonalInfoViewModel
import com.example.mohammedportfolio.viewmodel.ViewModelFactory
import kotlinx.coroutines.launch

class AboutFragment : Fragment() {

    private var _binding: FragmentAboutBinding? = null
    private val binding get() = _binding!!

    private val viewModel: PersonalInfoViewModel by viewModels {
        ViewModelFactory(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAboutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.personalInfo.collect { info ->
                    info?.let {
                        binding.tvInfoName.text = it.fullName.ifBlank { "—" }
                        binding.tvInfoDob.text = it.dateOfBirth.ifBlank { "—" }
                        binding.tvInfoBirthplace.text = it.birthplace.ifBlank { "—" }
                        binding.tvInfoGender.text = it.gender.ifBlank { "—" }
                        binding.tvInfoNationality.text = it.nationality.ifBlank { "—" }
                        binding.tvInfoPhone.text = it.phone.ifBlank { "—" }
                        binding.tvInfoEmail.text = it.email.ifBlank { "—" }
                        binding.tvInfoAddress.text = it.address.ifBlank { "—" }
                        binding.tvBiography.text = it.biography.ifBlank { "—" }
                        binding.tvCareerObjective.text = it.careerObjective.ifBlank { "—" }
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}