package com.hyun.storyspotter.util

import com.hyun.storyspotter.databinding.ActivityBookBinding
import java.lang.NullPointerException
import kotlin.Exception

class ExceptionDirectory : Exception() {
    fun nullPointerException(username: String, bookBinding: ActivityBookBinding) {
        try {
            bookBinding.tvBookSearchText.text = username
        } catch (e: NullPointerException) {
            print(e.printStackTrace())
        }
    }
}