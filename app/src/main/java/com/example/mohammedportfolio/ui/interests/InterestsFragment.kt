package com.example.mohammedportfolio.ui.interests

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
import com.example.mohammedportfolio.viewmodel.InterestViewModel
import com.example.mohammedportfolio.viewmodel.ViewModelFactory
import kotlinx.coroutines.launch

class InterestsFragment : Fragment() {

    private var container: LinearLayout? = null

    private val viewModel: InterestViewModel by viewModels {
        ViewModelFactory(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_interests, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        container = view.findViewById(R.id.interestsContainer)

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.allInterests.collect { interests ->
                    container?.removeAllViews()
                    if (interests.isEmpty()) {
                        val tv = TextView(requireContext()).apply {
                            text = "No interests added yet"
                            textSize = 15f
                            setTextColor(ContextCompat.getColor(requireContext(), R.color.teal_primary_dark))
                        }
                        container?.addView(tv)
                    } else {
                        for (i in interests) {
                            val tv = TextView(requireContext()).apply {
                                text = "•  ${i.name}"
                                textSize = 16f
                                setPadding(0, 12, 0, 12)
                                setTextColor(ContextCompat.getColor(requireContext(), R.color.teal_primary_dark))
                                setTypeface(null, Typeface.NORMAL)
                            }
                            container?.addView(tv)
                        }
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        container = null
    }
}