package com.example.onboardingflow.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import java.net.URL

class AuthViewModel : ViewModel() {
    private val auth = FirebaseAuth.getInstance()
    private val storage = FirebaseStorage.getInstance()

    private val _authState = MutableLiveData<AuthState>(AuthState.Initial)
    val authState: LiveData<AuthState> = _authState

    private val _profileImageUrl = MutableLiveData<String?>()
    val profileImageUrl: LiveData<String?> = _profileImageUrl

    fun signInWithGoogle(account: GoogleSignInAccount) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                _authState.postValue(AuthState.Loading)

                val credential = GoogleAuthProvider.getCredential(account.idToken, null)
                val authResult = auth.signInWithCredential(credential).await()

                // Lưu avatar URL từ Google vào Firebase Storage và user profile
                authResult.user?.let { user ->
                    saveAvatarToFirebaseStorage(account.photoUrl?.toString(), user.uid)
                    updateUserProfile(account.displayName, account.photoUrl?.toString())
                }

                _authState.postValue(AuthState.Success(authResult.user))
            } catch (e: Exception) {
                _authState.postValue(AuthState.Error(e.message ?: "Google sign in failed"))
            }
        }
    }

    private suspend fun saveAvatarToFirebaseStorage(photoUrl: String?, userId: String) {
        if (photoUrl.isNullOrEmpty()) return

        try {
            val inputStream = URL(photoUrl).openStream()
            val avatarRef = storage.reference
                .child("avatars")
                .child("$userId.jpg")

            avatarRef.putStream(inputStream).await()

            val downloadUrl = avatarRef.downloadUrl.await().toString()
            _profileImageUrl.postValue(downloadUrl)

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private suspend fun updateUserProfile(displayName: String?, photoUrl: String?) {
        val user = auth.currentUser ?: return

        val profileUpdates = UserProfileChangeRequest.Builder().apply {
            displayName?.let { setDisplayName(it) }
            photoUrl?.let {
                // Sử dụng URL từ Firebase Storage
                _profileImageUrl.value?.let { storageUrl ->
                    setPhotoUri(android.net.Uri.parse(storageUrl))
                }
            }
        }.build()

        user.updateProfile(profileUpdates).await()
    }

    fun getAvatarUrl(userId: String, callback: (String?) -> Unit) {
        val avatarRef = storage.reference
            .child("avatars")
            .child("$userId.jpg")

        avatarRef.downloadUrl
            .addOnSuccessListener { uri ->
                callback(uri.toString())
            }
            .addOnFailureListener {
                callback(null)
            }
    }

    fun uploadAvatar(imageUri: android.net.Uri, userId: String) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val avatarRef = storage.reference
                    .child("avatars")
                    .child("$userId.jpg")

                avatarRef.putFile(imageUri).await()
                val downloadUrl = avatarRef.downloadUrl.await().toString()
                _profileImageUrl.postValue(downloadUrl)

                // Cập nhật profile user
                updateUserProfilePhoto(downloadUrl)

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private suspend fun updateUserProfilePhoto(photoUrl: String) {
        val user = auth.currentUser ?: return

        val profileUpdates = UserProfileChangeRequest.Builder()
            .setPhotoUri(android.net.Uri.parse(photoUrl))
            .build()

        user.updateProfile(profileUpdates).await()
    }

    fun getProfileImageUrl(): String? {
        return _profileImageUrl.value
    }
}

sealed class AuthState {
    object Initial : AuthState()
    object Loading : AuthState()
    data class Success(val user: FirebaseUser?) : AuthState()
    data class Error(val message: String) : AuthState()
}