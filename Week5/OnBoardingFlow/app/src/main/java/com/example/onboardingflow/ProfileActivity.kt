package com.example.onboardingflow

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.onboardingflow.auth.AuthViewModel
import com.google.firebase.auth.FirebaseAuth

class ProfileActivity : AppCompatActivity() {

    private lateinit var authViewModel: AuthViewModel
    private val auth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        authViewModel = AuthViewModel()

        val userName = intent.getStringExtra("user_name") ?: "unknown"
        val userEmail = intent.getStringExtra("user_email") ?: "unknown"
        val userPhotoUrl = intent.getStringExtra("user_photo_url")

        val tvName = findViewById<TextView>(R.id.tvNameValue)
        val tvEmail = findViewById<TextView>(R.id.tvEmailValue)
        val tvDob = findViewById<TextView>(R.id.tvDobValue)
        val imageViewAvatar = findViewById<ImageView>(R.id.imageViewAvatar)

        tvName.text = userName
        tvEmail.text = userEmail
        tvDob.text = "unknown"

        val currentUser = auth.currentUser
        if (currentUser != null) {
            authViewModel.getAvatarUrl(currentUser.uid) { avatarUrl ->
                if (!avatarUrl.isNullOrEmpty()) {
                    loadImageWithGlide(avatarUrl, imageViewAvatar)
                } else {
                    // Fallback: sử dụng avatar từ Google
                    userPhotoUrl?.let { url ->
                        if (url.isNotEmpty()) {
                            loadImageWithGlide(url, imageViewAvatar)
                        } else {
                            imageViewAvatar.setImageResource(R.drawable.avatar)
                        }
                    }
                }
            }
        } else {
            userPhotoUrl?.let { url ->
                if (url.isNotEmpty()) {
                    loadImageWithGlide(url, imageViewAvatar)
                } else {
                    imageViewAvatar.setImageResource(R.drawable.avatar)
                }
            } ?: run {
                imageViewAvatar.setImageResource(R.drawable.avatar)
            }
        }
    }

    private fun loadImageWithGlide(imageUrl: String, imageView: ImageView) {
        Glide.with(this)
            .load(imageUrl)
            .circleCrop()
            .placeholder(R.drawable.avatar)
            .error(R.drawable.avatar)
            .into(imageView)
    }
}