package com.hyun.storyspotter.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.button.MaterialButton
import com.hyun.storyspotter.R
import com.hyun.storyspotter.adapter.OnboardingAdapter
import com.hyun.storyspotter.model.OnboardingItem
import com.hyun.storyspotter.ui.book.BookActivity
import com.hyun.storyspotter.viewmodel.OnBoardingViewModel


class MainActivity : AppCompatActivity() {
    private var onboardingAdapter: OnboardingAdapter? = null

    private lateinit var onBoardingViewModel: OnBoardingViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        onBoardingViewModel = ViewModelProvider(this)[OnBoardingViewModel::class.java]

        setupOnboardingItems()

        val onboardViewPager = findViewById<ViewPager2>(R.id.onboardingViewPager)
        onboardViewPager.adapter = onboardingAdapter

        val buttonOnBoardingAction: MaterialButton = findViewById(R.id.buttonOnboardingAction)

        buttonOnBoardingAction.setOnClickListener {
            val intent = Intent(this@MainActivity, BookActivity::class.java)
            startActivity(intent)
        }
    }

    private fun setupOnboardingItems() {
        val onboardingItems: MutableList<OnboardingItem> = onBoardingViewModel.createOnboardingItems()
        onboardingAdapter = OnboardingAdapter(onboardingItems)
    }
}