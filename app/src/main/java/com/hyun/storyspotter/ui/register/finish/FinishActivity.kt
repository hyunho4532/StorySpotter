package com.hyun.storyspotter.ui.register.finish

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.hyun.storyspotter.R
import com.hyun.storyspotter.databinding.ActivityFinishBinding

class FinishActivity : AppCompatActivity() {

    private lateinit var activityFinishBinding: ActivityFinishBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityFinishBinding = DataBindingUtil.setContentView(this, R.layout.activity_finish)

        val imageUrl = intent.getStringExtra("imageUrl")

        Glide.with(activityFinishBinding.root)
            .load(imageUrl)
            .into(activityFinishBinding.ivWelcomeLikeBook)
    }
}