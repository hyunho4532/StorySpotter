package com.hyun.storyspotter.ui.book

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.hyun.storyspotter.R
import com.hyun.storyspotter.adapter.BookAdapter
import com.hyun.storyspotter.databinding.ActivityBookBinding
import com.hyun.storyspotter.manager.BookSearchManager
import com.hyun.storyspotter.model.BookItem
import com.hyun.storyspotter.type.ImageType
import java.lang.NullPointerException

class BookActivity : AppCompatActivity() {

    private lateinit var bookAdapter: BookAdapter
    private val bookList = mutableListOf<BookItem>()
    private val bookSearchManager = BookSearchManager()
    private var imageType: ImageType = ImageType.UnAddImage

    private lateinit var username: String

    private lateinit var bookBinding: ActivityBookBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        bookBinding = DataBindingUtil.setContentView(this, R.layout.activity_book)

        val imageTypeString = intent.getStringExtra("imageType")
        Log.d("ImageTypeString", imageTypeString.toString())
        val imageType = if (imageTypeString == "AddImage") {
            ImageType.AddImage
        } else {
            ImageType.UnAddImage
        }
        bookBinding.imageType = imageType

        val username = intent.getStringExtra("username")
        if (username != null) {
            bookBinding.tvBookSearchText.text = username
        } else {
            bookBinding.tvBookSearchText.text = "Default Username"
        }

        val nickName = intent.getStringExtra("nickName")
        bookBinding.tvBookSearchText.text = nickName.toString()

        bookBinding.recyclerView.layoutManager = LinearLayoutManager(this)
        bookAdapter = BookAdapter(bookList)
        bookBinding.recyclerView.adapter = bookAdapter

        bookBinding.btnBookSearch.setOnClickListener {
            bookSearchManager.searchBooks(bookBinding.etBookSearch.text.toString(), bookBinding.etBookSearchCount.text.toString().toInt(), "0qMOlEnC8vFb9mMNLWKU", "pVr1B38BBk") { books ->
                runOnUiThread {
                    books?.let {
                        bookList.addAll(it)
                        bookAdapter.notifyDataSetChanged()
                    }
                }
            }
        }
    }
}