package com.hyun.storyspotter.event

import android.animation.Animator
import android.content.Context
import android.content.Intent
import com.airbnb.lottie.LottieAnimationView
import com.hyun.storyspotter.ui.main.MainActivity

class AddAnimatorListener {
    fun addAnimatorListener(context: Context, lotti: LottieAnimationView) {
        lotti.addAnimatorListener(object: Animator.AnimatorListener {
            override fun onAnimationStart(p0: Animator) {

            }

            override fun onAnimationEnd(p0: Animator) {
                val intent = Intent(context, MainActivity::class.java)
                context.startActivity(intent)
            }

            override fun onAnimationCancel(p0: Animator) {

            }

            override fun onAnimationRepeat(p0: Animator) {

            }

        })
    }
}