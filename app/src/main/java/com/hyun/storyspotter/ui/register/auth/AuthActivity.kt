package com.hyun.storyspotter.ui.register.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.hyun.storyspotter.R
import com.hyun.storyspotter.databinding.ActivityAuthBinding
import com.hyun.storyspotter.ui.book.BookActivity

class AuthActivity : AppCompatActivity() {

    private lateinit var authBinding: ActivityAuthBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        authBinding = DataBindingUtil.setContentView(this, R.layout.activity_auth)

        val photoUrl = intent.getStringExtra("photoUrl")

        Glide.with(authBinding.root)
            .load(photoUrl)
            .into(authBinding.ivGoogleProfile)

        authBinding.btnAuthInsert.setOnClickListener {
            val intent = Intent(this@AuthActivity, BookActivity::class.java)
            intent.putExtra("nickName", authBinding.usernameTextField.text.toString())
            startActivity(intent)
        }
    }
}