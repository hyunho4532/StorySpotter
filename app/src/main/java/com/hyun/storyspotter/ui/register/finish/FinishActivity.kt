package com.hyun.storyspotter.ui.register.finish

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.hyun.storyspotter.R
import com.hyun.storyspotter.databinding.ActivityFinishBinding

class FinishActivity : AppCompatActivity() {

    private lateinit var activityFinishBinding: ActivityFinishBinding
    private lateinit var database: FirebaseDatabase
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityFinishBinding = DataBindingUtil.setContentView(this, R.layout.activity_finish)

        database = FirebaseDatabase.getInstance()
        auth = FirebaseAuth.getInstance()

        val user = auth.currentUser!!

        val imageUrl = intent.getStringExtra("imageUrl")

        Glide.with(activityFinishBinding.root)
            .load(imageUrl)
            .into(activityFinishBinding.ivWelcomeLikeBook)

        val userReference = database.reference.child("users").child(user.uid)
        userReference.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (snapshot in dataSnapshot.children) {
                        val key = snapshot.key
                        val value = snapshot.value

                        val username = dataSnapshot.child("username").value.toString()

                        activityFinishBinding.tvFinishUser.text = username
                    }
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // 에러 처리
            }
        })
    }

}