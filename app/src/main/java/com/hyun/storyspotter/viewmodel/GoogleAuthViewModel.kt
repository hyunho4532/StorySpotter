package com.hyun.storyspotter.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import com.hyun.storyspotter.repository.GoogleAuthRepository

class GoogleAuthViewModel(private val googleAuthRepository: GoogleAuthRepository) : ViewModel() {
    fun googleSignIn(idToken: String?, applicationContext: Context) {
        googleAuthRepository.firebaseAuth(idToken, applicationContext)
    }
}