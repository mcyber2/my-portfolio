package com.example.mohammedportfolio.ui.hireme

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.mohammedportfolio.R
import com.example.mohammedportfolio.viewmodel.PersonalInfoViewModel
import com.example.mohammedportfolio.viewmodel.ViewModelFactory
import kotlinx.coroutines.launch

class HireMeFragment : Fragment() {

    private var tvContactEmail: TextView? = null
    private var tvContactPhone: TextView? = null

    private val viewModel: PersonalInfoViewModel by viewModels {
        ViewModelFactory(requireContext())
    }

    private var currentEmail: String = ""
    private var currentPhone: String = ""
    private var currentGithub: String = ""
    private var currentLinkedin: String = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_hire_me, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tvContactEmail = view.findViewById(R.id.tvContactEmail)
        tvContactPhone = view.findViewById(R.id.tvContactPhone)

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.personalInfo.collect { info ->
                    info?.let {
                        currentEmail = it.email
                        currentPhone = it.phone
                        currentGithub = it.githubUrl
                        currentLinkedin = it.linkedinUrl
                        tvContactEmail?.text = "✉️  Email: ${it.email.ifBlank { "—" }}"
                        tvContactPhone?.text = "📞  Phone: ${it.phone.ifBlank { "—" }}"
                    }
                }
            }
        }

        view.findViewById<View>(R.id.btnEmail)?.setOnClickListener {
            if (currentEmail.isBlank() || currentEmail == "Placeholder") {
                Toast.makeText(requireContext(), "Email not set yet", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val intent = Intent(Intent.ACTION_SENDTO).apply {
                data = Uri.parse("mailto:$currentEmail")
                putExtra(Intent.EXTRA_SUBJECT, "Opportunity for Mohammed")
            }
            startActivity(intent)
        }

        view.findViewById<View>(R.id.btnCall)?.setOnClickListener {
            if (currentPhone.isBlank() || currentPhone == "Placeholder") {
                Toast.makeText(requireContext(), "Phone not set yet", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val intent = Intent(Intent.ACTION_DIAL).apply {
                data = Uri.parse("tel:$currentPhone")
            }
            startActivity(intent)
        }

        view.findViewById<View>(R.id.btnGitHub)?.setOnClickListener {
            if (currentGithub.isBlank()) {
                Toast.makeText(requireContext(), "GitHub URL not set — edit in Admin → Personal Info", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }
            openUrl(currentGithub)
        }

        view.findViewById<View>(R.id.btnLinkedIn)?.setOnClickListener {
            if (currentLinkedin.isBlank()) {
                Toast.makeText(requireContext(), "LinkedIn URL not set — edit in Admin → Personal Info", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }
            openUrl(currentLinkedin)
        }
    }

    private fun openUrl(url: String) {
        try {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(intent)
        } catch (e: Exception) {
            Toast.makeText(requireContext(), "Cannot open URL: $url", Toast.LENGTH_SHORT).show()
        }
    }
}