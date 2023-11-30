package com.hyun.storyspotter.ui.book.detail

import android.app.ActivityOptions
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Pair
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.hyun.storyspotter.R
import com.hyun.storyspotter.databinding.ActivityBookDetailBinding

class BookDetailActivity : AppCompatActivity() {

    private lateinit var activityBookDetailBinding: ActivityBookDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityBookDetailBinding = DataBindingUtil.setContentView(this, R.layout.activity_book_detail)

        val image = intent.getStringExtra("image")
        val title = intent.getStringExtra("title")

        Glide.with(activityBookDetailBinding.root)
            .load(image)
            .into(activityBookDetailBinding.ivDetailBook)

        activityBookDetailBinding.tvDetailBook.text = title
    }
}