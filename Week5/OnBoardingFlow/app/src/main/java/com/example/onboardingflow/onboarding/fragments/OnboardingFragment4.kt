package com.example.onboardingflow.onboarding.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.onboardingflow.R
import com.example.onboardingflow.ProfileActivity
import com.example.onboardingflow.auth.AuthViewModel
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.material.snackbar.Snackbar
import com.example.onboardingflow.auth.AuthState

class OnboardingFragment4 : Fragment() {

    private lateinit var btnGoogleSignIn: android.widget.Button
    private lateinit var progressBar: android.widget.ProgressBar

    private val authViewModel: AuthViewModel by viewModels()
    private lateinit var googleSignInClient: GoogleSignInClient

    companion object {
        private const val RC_GOOGLE_SIGN_IN = 1001
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_onboarding4, container, false)
        initViews(view)
        setupGoogleSignIn()
        observeAuthState()
        return view
    }

    private fun initViews(view: View) {
        btnGoogleSignIn = view.findViewById(R.id.btnGoogleSignIn)
        progressBar = view.findViewById(R.id.progressBar)
    }

    private fun setupGoogleSignIn() {
        val webClientId = getString(R.string.default_web_client_id)

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(webClientId)
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(requireActivity(), gso)

        btnGoogleSignIn.setOnClickListener {
            val signInIntent = googleSignInClient.signInIntent
            startActivityForResult(signInIntent, RC_GOOGLE_SIGN_IN)
        }
    }

    private fun observeAuthState() {
        authViewModel.authState.observe(viewLifecycleOwner) { state ->
            when (state) {
                is AuthState.Loading -> {
                    progressBar.visibility = View.VISIBLE
                    btnGoogleSignIn.visibility = View.GONE
                }
                is AuthState.Success -> {
                    progressBar.visibility = View.GONE
                    showMessage("Login successful!")
                    navigateToProfile(state.user)
                }
                is AuthState.Error -> {
                    progressBar.visibility = View.GONE
                    btnGoogleSignIn.visibility = View.VISIBLE
                    showMessage("Login failed: ${state.message}")
                }
                else -> {
                    progressBar.visibility = View.GONE
                    btnGoogleSignIn.visibility = View.VISIBLE
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RC_GOOGLE_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val account = task.getResult(ApiException::class.java)
                account?.let {
                    authViewModel.signInWithGoogle(it)
                }
            } catch (e: ApiException) {
                showMessage("Google sign in failed: ${e.statusCode}")
            }
        }
    }

    private fun navigateToProfile(user: com.google.firebase.auth.FirebaseUser?) {
        val intent = Intent(requireActivity(), ProfileActivity::class.java).apply {
            putExtra("user_name", user?.displayName ?: "User")
            putExtra("user_email", user?.email ?: "")
        }
        startActivity(intent)
        requireActivity().finish()
    }

    private fun showMessage(message: String) {
        Snackbar.make(requireView(), message, Snackbar.LENGTH_LONG).show()
    }
}