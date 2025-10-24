package com.example.smarttasks.ui

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.smarttasks.R
import com.example.smarttasks.databinding.FragmentVerifyCodeBinding
import com.example.smarttasks.viewmodel.PasswordResetViewModel

class VerifyCodeFragment : Fragment() {
    private var _binding: FragmentVerifyCodeBinding? = null
    private val binding get() = _binding!!
    private val viewModel: PasswordResetViewModel by activityViewModels()

    private val codeEditTexts = mutableListOf<android.widget.EditText>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentVerifyCodeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupCodeInputs()
        setupTextWatchers()

        binding.ivBack.setOnClickListener {
            findNavController().navigateUp()
        }

        binding.btnNext.setOnClickListener {
            val code = getVerificationCode()
            if (code.length == 6) {
                viewModel.setVerificationCode(code)
                findNavController().navigate(R.id.action_verifyCodeFragment_to_createPasswordFragment)
            } else {
                binding.tvError.visibility = View.VISIBLE
                binding.tvError.text = "Vui lòng nhập đủ 6 chữ số"
            }
        }
    }

    private fun setupCodeInputs() {
        codeEditTexts.clear()
        codeEditTexts.addAll(listOf(
            binding.etCode1,
            binding.etCode2,
            binding.etCode3,
            binding.etCode4,
            binding.etCode5,
            binding.etCode6
        ))

        // Set input type to number for all EditTexts
        codeEditTexts.forEach { editText ->
            editText.inputType = android.text.InputType.TYPE_CLASS_NUMBER
            editText.filters = arrayOf(android.text.InputFilter.LengthFilter(1))
        }
    }

    private fun setupTextWatchers() {
        for (i in codeEditTexts.indices) {
            codeEditTexts[i].addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

                override fun afterTextChanged(s: Editable?) {
                    // Auto move to next field when text is entered
                    if (s?.length == 1 && i < codeEditTexts.size - 1) {
                        codeEditTexts[i + 1].requestFocus()
                    }

                    if (s?.isEmpty() == true && i > 0) {
                        codeEditTexts[i - 1].requestFocus()
                    }

                    binding.tvError.visibility = View.GONE

                    if (getVerificationCode().length == 6) {
                        binding.btnNext.performClick()
                    }
                }
            })

            codeEditTexts[i].setOnKeyListener { _, keyCode, event ->
                if (keyCode == android.view.KeyEvent.KEYCODE_DEL &&
                    event.action == android.view.KeyEvent.ACTION_DOWN &&
                    codeEditTexts[i].text.isEmpty() && i > 0) {
                    codeEditTexts[i - 1].requestFocus()
                    codeEditTexts[i - 1].text.clear()
                    true
                } else {
                    false
                }
            }
        }
    }

    private fun getVerificationCode(): String {
        return codeEditTexts.joinToString("") { it.text.toString() }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}