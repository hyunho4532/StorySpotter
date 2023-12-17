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

class BookActivity : AppCompatActivity() {

    private lateinit var bookAdapter: BookAdapter
    private val bookList = mutableListOf<BookItem>()
    private val bookSearchManager = BookSearchManager()

    private lateinit var bookBinding: ActivityBookBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        bookBinding = DataBindingUtil.setContentView(this, R.layout.activity_book)

        val intentTypeString = intent.getStringExtra("intentType")

        if (intentTypeString == "UnLikeMove") {
            val username = intent.getStringExtra("username")
            bookBinding.tvBookSearchText.text = username.toString()
            Log.e("username", username.toString())
            bookBinding.tvBookType.text = intentTypeString.toString()
            bookBinding.tvBookSearchSubText.text = "님, 읽고 싶은 책을 선택해주세요!"
        } else {
            val nickName = intent.getStringExtra("nickName")
            bookBinding.tvBookSearchText.text = nickName.toString()
            bookBinding.tvBookType.text = null
            bookBinding.tvBookSearchSubText.text = "님, 좋아하는 책을 선택해주세요!"
        }

        bookBinding.recyclerView.layoutManager = LinearLayoutManager(this)
        bookAdapter = BookAdapter(bookList, bookBinding.tvBookType.text.toString())
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