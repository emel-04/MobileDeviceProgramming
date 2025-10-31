package com.example.onboardingflow

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlin.jvm.java
import com.example.onboardingflow.onboarding.OnboardingActivity
import android.content.Intent

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding)

        val intent = Intent(this, OnboardingActivity::class.java)
        startActivity(intent)
    }
}