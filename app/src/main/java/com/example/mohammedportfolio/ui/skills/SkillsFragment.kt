package com.example.mohammedportfolio.ui.skills

import android.graphics.Typeface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.mohammedportfolio.R
import com.example.mohammedportfolio.data.entity.SkillEntity
import com.example.mohammedportfolio.databinding.FragmentSkillsBinding
import com.example.mohammedportfolio.viewmodel.SkillViewModel
import com.example.mohammedportfolio.viewmodel.ViewModelFactory
import kotlinx.coroutines.launch

class SkillsFragment : Fragment() {

    private var _binding: FragmentSkillsBinding? = null
    private val binding get() = _binding!!

    private val viewModel: SkillViewModel by viewModels {
        ViewModelFactory(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSkillsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.allSkills.collect { skills ->
                    renderSkills(skills)
                }
            }
        }
    }

    private fun renderSkills(skills: List<SkillEntity>) {
        binding.skillsContainer.removeAllViews()
        if (skills.isEmpty()) {
            val empty = TextView(requireContext()).apply {
                text = "No skills added yet"
                textSize = 15f
                setTextColor(ContextCompat.getColor(requireContext(), android.R.color.darker_gray))
            }
            binding.skillsContainer.addView(empty)
            return
        }

        // Group by category
        val grouped = skills.groupBy { it.category }

        for ((category, list) in grouped) {
            // Category header
            val header = TextView(requireContext()).apply {
                text = category
                textSize = 18f
                setTypeface(null, Typeface.BOLD)
                setTextColor(ContextCompat.getColor(requireContext(), R.color.teal_primary_dark))
                setPadding(0, 24, 0, 8)
            }
            binding.skillsContainer.addView(header)

            // Skills under this category
            for (skill in list) {
                val row = LinearLayout(requireContext()).apply {
                    orientation = LinearLayout.HORIZONTAL
                    setPadding(0, 12, 0, 12)
                }

                val name = TextView(requireContext()).apply {
                    text = "•  ${skill.name}"
                    textSize = 15f
                    layoutParams = LinearLayout.LayoutParams(
                        0, LinearLayout.LayoutParams.WRAP_CONTENT, 1f
                    )
                }

                val level = TextView(requireContext()).apply {
                    text = skill.level
                    textSize = 12f
                    setTypeface(null, Typeface.BOLD)
                    setTextColor(ContextCompat.getColor(requireContext(), R.color.teal_primary_dark))
                }

                row.addView(name)
                row.addView(level)
                binding.skillsContainer.addView(row)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}