package com.hyun.storyspotter.viewmodel

import android.widget.TextView
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class FinishViewModel : ViewModel() {

    fun getUsername(
        userId: FirebaseUser,
        database: FirebaseDatabase,
        onSuccess: (String) -> Unit,
        onFailure: () -> Unit
    ) {
        val userReference = database.reference.child("users").child(userId.uid)
        userReference.child("username").addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val username = dataSnapshot.value.toString()
                onSuccess(username)
            }

            override fun onCancelled(databaseError: DatabaseError) {
                onFailure()
            }
        })
    }
}