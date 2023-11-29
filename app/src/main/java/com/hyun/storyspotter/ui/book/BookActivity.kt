package com.hyun.storyspotter.ui.book

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hyun.storyspotter.R
import com.hyun.storyspotter.adapter.BookAdapter
import com.hyun.storyspotter.manager.BookSearchManager
import com.hyun.storyspotter.model.BookItem

class BookActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var bookAdapter: BookAdapter
    private val bookList = mutableListOf<BookItem>()
    private val bookSearchManager = BookSearchManager()

    private lateinit var bookSearchEditText: EditText

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book)

        recyclerView = findViewById(R.id.recyclerView)
        bookSearchEditText = findViewById(R.id.et_book_search)

        recyclerView.layoutManager = LinearLayoutManager(this)
        bookAdapter = BookAdapter(bookList)
        recyclerView.adapter = bookAdapter

        bookSearchManager.searchBooks(bookSearchEditText.text.toString(), 10, "0qMOlEnC8vFb9mMNLWKU", "pVr1B38BBk") { books ->
            runOnUiThread {
                books?.let {
                    bookList.addAll(it)
                    bookAdapter.notifyDataSetChanged()
                }
            }
        }
    }
}