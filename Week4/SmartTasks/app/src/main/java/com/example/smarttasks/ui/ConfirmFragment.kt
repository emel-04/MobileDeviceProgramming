package com.example.smarttasks.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.smarttasks.R
import com.example.smarttasks.databinding.FragmentConfirmBinding
import com.example.smarttasks.viewmodel.PasswordResetViewModel

class ConfirmFragment : Fragment() {
    private var _binding: FragmentConfirmBinding? = null
    private val binding get() = _binding!!
    private val viewModel: PasswordResetViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentConfirmBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.passwordResetData.observe(viewLifecycleOwner) { data ->
            binding.tvEmail.setText(data.email)
            binding.tvCode.setText(data.verificationCode)
            binding.tvPassword.setText("•".repeat(data.newPassword.length))
        }

        binding.ivBack.setOnClickListener {
            findNavController().navigateUp()
        }

        binding.btnSubmit.setOnClickListener {
            // Navigate back to first screen and keep data
            findNavController().navigate(R.id.action_confirmFragment_to_forgotPasswordFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}