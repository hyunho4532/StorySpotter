package com.hyun.storyspotter.repository

import android.content.Context
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.database.FirebaseDatabase

class GoogleAuthRepository {
    private lateinit var auth: FirebaseAuth
    private lateinit var database: FirebaseDatabase

    fun firebaseAuth(idToken: String?, context: Context) {
        val credential: AuthCredential = GoogleAuthProvider.getCredential(idToken, null)

        auth.signInWithCredential(credential).addOnCompleteListener {
            if (it.isSuccessful) {
                val user: FirebaseUser = auth.currentUser!!

                val map: HashMap<String, String> = HashMap()

                map["id"] = user.uid
                map["name"] = user.displayName.toString()
                map["profile"] = user.photoUrl.toString()

                database.reference.child("users").child(user.uid).setValue(map)
            }
        }
    }
}