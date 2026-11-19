package com.example.mohammedportfolio.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.mohammedportfolio.R
import com.example.mohammedportfolio.viewmodel.PersonalInfoViewModel
import com.example.mohammedportfolio.viewmodel.ViewModelFactory
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {

    private var tvName: TextView? = null
    private var tvBio: TextView? = null

    private val viewModel: PersonalInfoViewModel by viewModels {
        ViewModelFactory(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tvName = view.findViewById(R.id.tvName)
        tvBio = view.findViewById(R.id.tvBio)

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.personalInfo.collect { info ->
                    info?.let {
                        tvName?.text = it.fullName
                        tvBio?.text = it.biography.ifBlank {
                            "Passionate about building clean, user-friendly Android applications."
                        }
                    }
                }
            }
        }

        // Card click listeners
        view.findViewById<View>(R.id.cardEducation)?.setOnClickListener {
            findNavController().navigate(R.id.action_home_to_education)
        }
        view.findViewById<View>(R.id.cardSkills)?.setOnClickListener {
            findNavController().navigate(R.id.action_home_to_skills)
        }
        view.findViewById<View>(R.id.cardProjects)?.setOnClickListener {
            activity?.findViewById<com.google.android.material.bottomnavigation.BottomNavigationView>(
                R.id.bottomNavigation
            )?.selectedItemId = R.id.projectsFragment
        }
        view.findViewById<View>(R.id.cardExperience)?.setOnClickListener {
            findNavController().navigate(R.id.action_home_to_experience)
        }
        view.findViewById<View>(R.id.cardCertificates)?.setOnClickListener {
            findNavController().navigate(R.id.action_home_to_certificates)
        }
        view.findViewById<View>(R.id.cardInterests)?.setOnClickListener {
            findNavController().navigate(R.id.action_home_to_interests)
        }
        view.findViewById<View>(R.id.cardTeachers)?.setOnClickListener {
            findNavController().navigate(R.id.action_home_to_teachers)
        }
        view.findViewById<View>(R.id.btnAboutMe)?.setOnClickListener {
            activity?.findViewById<com.google.android.material.bottomnavigation.BottomNavigationView>(
                R.id.bottomNavigation
            )?.selectedItemId = R.id.aboutFragment
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        tvName = null
        tvBio = null
    }
}