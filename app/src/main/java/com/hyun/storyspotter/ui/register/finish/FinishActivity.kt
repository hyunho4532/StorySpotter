package com.hyun.storyspotter.ui.register.finish

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.hyun.storyspotter.R
import com.hyun.storyspotter.databinding.ActivityFinishBinding
import com.hyun.storyspotter.viewmodel.FinishViewModel

class FinishActivity : AppCompatActivity() {

    private lateinit var activityFinishBinding: ActivityFinishBinding
    private lateinit var database: FirebaseDatabase
    private lateinit var auth: FirebaseAuth
    private lateinit var finishViewModel: FinishViewModel

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

        finishViewModel.getUsername(user, database, { username ->
            activityFinishBinding.tvFinishUser.text = username
        }) {

        }
    }

}