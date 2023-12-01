package com.hyun.storyspotter.ui.register.auth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.hyun.storyspotter.R
import com.hyun.storyspotter.databinding.ActivityAuthBinding

class AuthActivity : AppCompatActivity() {

    private lateinit var authBinding: ActivityAuthBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        authBinding = DataBindingUtil.setContentView(this, R.layout.activity_auth)

        val photoUrl = intent.getStringExtra("photoUrl")

        Glide.with(authBinding.root)
            .load(photoUrl)
            .into(authBinding.ivGoogleProfile)
    }
}