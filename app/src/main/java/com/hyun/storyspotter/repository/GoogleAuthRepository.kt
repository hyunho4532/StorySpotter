package com.hyun.storyspotter.repository

import android.content.Context
import android.content.Intent
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.database.FirebaseDatabase
import com.hyun.storyspotter.ui.register.auth.AuthActivity

class GoogleAuthRepository(
    private val auth: FirebaseAuth = FirebaseAuth.getInstance(),
    private val database: FirebaseDatabase = FirebaseDatabase.getInstance()
) {
    fun firebaseAuth(idToken: String?, applicationContext: Context) {
        val credential: AuthCredential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential).addOnCompleteListener {
            if (it.isSuccessful) {
                val user: FirebaseUser = auth.currentUser!!

                val map: HashMap<String, String> = HashMap()
                map["id"] = user.uid
                map["name"] = user.displayName.toString()
                map["profile"] = user.photoUrl.toString()

                database.reference.child("users").child(user.uid).setValue(map)

                val intent = Intent(applicationContext, AuthActivity::class.java)
                intent.putExtra("photoUrl", user.photoUrl.toString())
                applicationContext.startActivity(intent)
            }
        }
    }
}