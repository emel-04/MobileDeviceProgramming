package com.example.smarttasks.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.smarttasks.R
import com.example.smarttasks.databinding.FragmentCreatePasswordBinding
import com.example.smarttasks.viewmodel.PasswordResetViewModel

class CreatePasswordFragment : Fragment() {
    private var _binding: FragmentCreatePasswordBinding? = null
    private val binding get() = _binding!!
    private val viewModel: PasswordResetViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCreatePasswordBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.ivBack.setOnClickListener {
            findNavController().navigateUp()
        }

        binding.btnNext.setOnClickListener {
            val password = binding.etPassword.text.toString()
            val confirmPassword = binding.etConfirmPassword.text.toString()

            if (password.isNotEmpty() && password == confirmPassword) {
                viewModel.setNewPassword(password)
                findNavController().navigate(R.id.action_createPasswordFragment_to_confirmFragment)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}