package com.hyun.storyspotter.ui.book

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
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