package com.hyun.storyspotter.ui.book.read

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.hyun.storyspotter.R
import com.hyun.storyspotter.databinding.ActivityBookReadBinding

class BookReadActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBookReadBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_book_read)

        val imageUrl = intent.getStringExtra("imageUrl")
        val title = intent.getStringExtra("title")

        Glide.with(binding.root)
            .load(imageUrl)
            .into(binding.ivReadBook)

        binding.tvReadBookTitle.text = title


    }
}