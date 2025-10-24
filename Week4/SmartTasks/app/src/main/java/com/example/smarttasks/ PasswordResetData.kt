package com.example.smarttasks

data class PasswordResetData(
    val email: String = "",
    val verificationCode: String = "",
    val newPassword: String = ""
)