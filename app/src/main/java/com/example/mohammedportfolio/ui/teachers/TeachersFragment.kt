package com.example.mohammedportfolio.ui.teachers

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
import androidx.recyclerview.widget.RecyclerView
import com.example.mohammedportfolio.R
import com.example.mohammedportfolio.viewmodel.FavoriteTeacherViewModel
import com.example.mohammedportfolio.viewmodel.ViewModelFactory
import kotlinx.coroutines.launch

class TeachersFragment : Fragment() {

    private var recyclerView: RecyclerView? = null

    private val viewModel: FavoriteTeacherViewModel by viewModels {
        ViewModelFactory(requireContext())
    }

    private lateinit var adapter: TeachersAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_teachers, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.findViewById(R.id.rvTeachers)
        adapter = TeachersAdapter()
        recyclerView?.layoutManager = LinearLayoutManager(requireContext())
        recyclerView?.adapter = adapter

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.allTeachers.collect { adapter.updateList(it) }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        recyclerView = null
    }
}