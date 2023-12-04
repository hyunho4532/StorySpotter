package com.hyun.storyspotter.ui.book

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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

    private lateinit var bookBinding: ActivityBookBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        bookBinding = DataBindingUtil.setContentView(this, R.layout.activity_book)

        try {
            val imageTypeString = intent.getStringExtra("imageType")

            val imageType = if (imageTypeString == "UnAddImage") {
                ImageType.UnAddImage
            } else {
                ImageType.AddImage
            }

            bookBinding.imageType = imageType

        } catch (e: NullPointerException) {
            bookBinding.imageType = imageType
        }

        val nickName = intent.getStringExtra("nickName")

        bookBinding.recyclerView.layoutManager = LinearLayoutManager(this)
        bookAdapter = BookAdapter(bookList)
        bookBinding.recyclerView.adapter = bookAdapter

        bookBinding.tvBookSearchText.text = nickName.toString()

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