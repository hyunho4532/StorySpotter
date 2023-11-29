package com.hyun.storyspotter.ui

import android.animation.Animator
import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import com.hyun.storyspotter.R
import com.hyun.storyspotter.databinding.ActivitySplashBinding

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_splash)

        binding.lotti.addAnimatorListener(object: Animator.AnimatorListener {
            override fun onAnimationStart(animatorStart: Animator) {

            }

            override fun onAnimationEnd(animatorEnd: Animator) {

                val intent = Intent(this@SplashActivity, MainActivity::class.java)
                startActivity(intent)
            }

            override fun onAnimationCancel(animatorCancel: Animator) {

            }

            override fun onAnimationRepeat(animationRepeat: Animator) {

            }
        })
    }
}