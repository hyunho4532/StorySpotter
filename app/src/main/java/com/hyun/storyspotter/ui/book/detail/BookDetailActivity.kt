package com.hyun.storyspotter.ui.book.detail

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.hyun.storyspotter.R
import com.hyun.storyspotter.databinding.ActivityBookDetailBinding
import com.hyun.storyspotter.type.BookType
import com.hyun.storyspotter.type.ImageType
import com.hyun.storyspotter.ui.book.read.BookReadActivity
import com.hyun.storyspotter.ui.register.auth.AuthActivity
import com.hyun.storyspotter.ui.register.finish.FinishActivity

class BookDetailActivity : AppCompatActivity() {

    private lateinit var activityBookDetailBinding: ActivityBookDetailBinding

    private var bookType: BookType = BookType.BookInsert

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityBookDetailBinding = DataBindingUtil.setContentView(this, R.layout.activity_book_detail)

        val image = intent.getStringExtra("image")
        val title = intent.getStringExtra("title")
        val author = intent.getStringExtra("author")
        val description = intent.getStringExtra("description")

        activityBookDetailBinding.btnBookDetailLike.text = bookType.toString()

        Glide.with(activityBookDetailBinding.root)
            .load(image)
            .into(activityBookDetailBinding.ivDetailBook)

        activityBookDetailBinding.tvDetailBook.text = title
        activityBookDetailBinding.tvDetailBookAuthor.text = author
        activityBookDetailBinding.tvDetailBookDescription.text = description

        activityBookDetailBinding.btnBookDetailLike.setOnClickListener {
            if (bookType == BookType.BookInsert) {
                val intent = Intent(this@BookDetailActivity, FinishActivity::class.java)
                intent.putExtra("imageUrl", image)
                intent.putExtra("title", title)
                startActivity(intent)
            } else {
                val intent = Intent(this@BookDetailActivity, AuthActivity::class.java)
                intent.putExtra("imageUrl", image)
                intent.putExtra("title", title)
                startActivity(intent)
            }
        }
    }
}