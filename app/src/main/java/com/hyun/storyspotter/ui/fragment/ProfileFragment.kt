package com.hyun.storyspotter.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.hyun.storyspotter.R
import com.hyun.storyspotter.util.GetToLet

class ProfileFragment : Fragment() {

    private lateinit var auth: FirebaseAuth
    private lateinit var myRef: DatabaseReference

    private val getToLet: GetToLet = GetToLet()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view: View = inflater.inflate(R.layout.fragment_profile, container, false)

        auth = FirebaseAuth.getInstance()

        auth.currentUser?.uid?.let { uid ->
            val firebaseDatabase: FirebaseDatabase = FirebaseDatabase.getInstance()
            myRef = firebaseDatabase.getReference("users").child(uid)
        }

        myRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val profile = snapshot.child("profile").getValue(String::class.java)
                val hobby = snapshot.child("hobby").getValue(String::class.java)
                val username = snapshot.child("username").getValue(String::class.java)

                profile?.let {
                    getToLet.getLetGlideImageSource(requireContext(), profile, view)
                }


                hobby?.let {
                    getToLet.getLetUsernameAndHobby(username, hobby, view)
                }
            }

            override fun onCancelled(error: DatabaseError) {

            }
        })

        return view
    }
}