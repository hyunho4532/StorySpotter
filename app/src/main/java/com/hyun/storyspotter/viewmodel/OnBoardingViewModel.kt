package com.hyun.storyspotter.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.button.MaterialButton
import com.hyun.storyspotter.R
import com.hyun.storyspotter.model.OnboardingItem

class OnBoardingViewModel: ViewModel() {
    private val _nextPage = MutableLiveData<Unit>()
    val nextPage: LiveData<Unit> = _nextPage

    fun createOnboardingItems(): MutableList<OnboardingItem> {
        val onboardingItems: MutableList<OnboardingItem> = mutableListOf()

        val onboardMainPage = OnboardingItem().apply {
            title = "안녕하세요! 반갑습니다"
            description = "스토리 스토퍼는 다양한 책 서비스를 제공합니다."
            lottieAnimationRes = R.raw.onboarding_main_screen_lottie
        }
        onboardingItems.add(onboardMainPage)

        val onboardSubPage = OnboardingItem().apply {
            title = "요즘 읽을 만한 책이 없으시다고요?"
            description = "스토리 스토퍼한테 맡겨보세요!!"
            lottieAnimationRes = R.raw.onboarding_sub_screen_lottie
        }
        onboardingItems.add(onboardSubPage)

        return onboardingItems
    }
}