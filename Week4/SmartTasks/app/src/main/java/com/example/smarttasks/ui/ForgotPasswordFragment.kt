package com.example.smarttasks.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.smarttasks.R
import com.example.smarttasks.databinding.FragmentForgotPasswordBinding
import com.example.smarttasks.viewmodel.PasswordResetViewModel

class ForgotPasswordFragment : Fragment() {
    private var _binding: FragmentForgotPasswordBinding? = null
    private val binding get() = _binding!!
    private val viewModel: PasswordResetViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentForgotPasswordBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Display submitted data if exists
        viewModel.passwordResetData.observe(viewLifecycleOwner) { data ->
            if (data.email.isNotEmpty()) {
                binding.tvSubmittedData.visibility = View.VISIBLE
                binding.tvSubmittedData.text = """
                    Submitted Data:
                    Email: ${data.email}
                    Code: ${data.verificationCode}
                    Password: ${data.newPassword}
                """.trimIndent()
            }
        }

        binding.btnNext.setOnClickListener {
            val email = binding.etEmail.text.toString()
            if (email.isNotEmpty()) {
                viewModel.setEmail(email)
                findNavController().navigate(R.id.action_forgotPasswordFragment_to_verifyCodeFragment)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}