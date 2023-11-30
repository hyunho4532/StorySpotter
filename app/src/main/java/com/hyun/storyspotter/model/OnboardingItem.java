package com.hyun.storyspotter.model;

public class OnboardingItem {
    private int lottieAnimationRes;
    private String title;
    private String description;

    public int getLottieAnimationRes() {
        return lottieAnimationRes;
    }

    public void setLottieAnimationRes(int lottieAnimationRes) {
        this.lottieAnimationRes = lottieAnimationRes;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
