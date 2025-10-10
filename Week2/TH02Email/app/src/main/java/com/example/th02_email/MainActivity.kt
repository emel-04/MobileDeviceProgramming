package com.example.th02_email

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.content.Context

class MainActivity : AppCompatActivity() {

    private lateinit var emailEditText: EditText
    private lateinit var errorTextView: TextView
    private lateinit var checkButton: Button
    private lateinit var resultTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViews()
        setupButtonClickListener()
    }

    private fun initViews() {
        emailEditText = findViewById(R.id.emailEditText)
        errorTextView = findViewById(R.id.errorTextView)
        checkButton = findViewById(R.id.checkButton)
        resultTextView = findViewById(R.id.resultTextView)

        errorTextView.visibility = View.GONE
        resultTextView.text = ""
    }

    private fun setupButtonClickListener() {
        checkButton.setOnClickListener {
            hideKeyboard()
            validateEmail()
        }
    }

    private fun validateEmail() {
        val email = emailEditText.text.toString().trim()

        when {
            email.isEmpty() -> {
                showError("Email không hợp lệ")
                showResult("")
            }
            !email.contains("@") -> {
                showError("Email không đúng định dạng")
                showResult("")
            }
            else -> {
                hideError()
                showResult("Bạn đã nhập email hợp lệ")
            }
        }
    }

    private fun showError(message: String) {
        errorTextView.text = message
        errorTextView.setTextColor(Color.RED)
        errorTextView.visibility = View.VISIBLE
    }

    private fun hideError() {
        errorTextView.visibility = View.GONE
    }

    private fun showResult(message: String) {
        resultTextView.text = message
        if (message.isNotEmpty()) {
            resultTextView.setTextColor(Color.GREEN)
        } else {
            resultTextView.setTextColor(Color.BLACK)
        }
    }

    private fun hideKeyboard() {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(emailEditText.windowToken, 0)
    }
}