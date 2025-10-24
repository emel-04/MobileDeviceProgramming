package com.example.smarttasks.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.smarttasks.PasswordResetData

class PasswordResetViewModel : ViewModel() {
    private val _passwordResetData = MutableLiveData<PasswordResetData>(PasswordResetData())
    val passwordResetData: LiveData<PasswordResetData> = _passwordResetData

    fun setEmail(email: String) {
        _passwordResetData.value = _passwordResetData.value?.copy(email = email)
    }

    fun setVerificationCode(code: String) {
        _passwordResetData.value = _passwordResetData.value?.copy(verificationCode = code)
    }

    fun setNewPassword(password: String) {
        _passwordResetData.value = _passwordResetData.value?.copy(newPassword = password)
    }
}