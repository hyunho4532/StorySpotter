package com.hyun.storyspotter.ui.book

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.hyun.storyspotter.R
import com.hyun.storyspotter.adapter.BookAdapter
import com.hyun.storyspotter.databinding.ActivityBookBinding
import com.hyun.storyspotter.manager.BookSearchManager
import com.hyun.storyspotter.model.BookItem
import com.hyun.storyspotter.util.GetToLet
import com.jakewharton.rxbinding4.widget.textChanges
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class BookActivity : AppCompatActivity() {

    private lateinit var bookAdapter: BookAdapter
    private val bookList = mutableListOf<BookItem>()
    private val bookSearchManager = BookSearchManager()

    private lateinit var auth: FirebaseAuth
    private lateinit var unBookMyRef: DatabaseReference

    private lateinit var bookBinding: ActivityBookBinding

    private lateinit var firebaseDatabase: FirebaseDatabase

    private val getToLet: GetToLet = GetToLet()

    /**
        val observableTextQuery = Observable
            .create(/* observable 추가 */)
            .debounce(500, TimeUnit.MILLISECONDS)  //입력 후 0.5간 추가 입력이 없어야만 작동
            .subscribeOn(Schedulers.io())  //새로운 스레드에서 작업
     */

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        bookBinding = DataBindingUtil.setContentView(this, R.layout.activity_book)

        auth = FirebaseAuth.getInstance()

        /**
         * "users" {
         *      "firebase-uid" {
         *          "username": "username 값"
         *      }
         * }
         * **/
        auth.currentUser?.uid?.let { uid ->
            val firebaseDatabase: FirebaseDatabase = FirebaseDatabase.getInstance()
            unBookMyRef = firebaseDatabase.getReference("users").child(uid)
        }

        unBookMyRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val username = snapshot.child("username").getValue(String::class.java)

                bookBinding.tvBookSearchText.text = username.toString()
            }

            override fun onCancelled(error: DatabaseError) {

            }
        })

        val intentTypeString = intent.getStringExtra("intentType")

        if (intentTypeString == "UnLikeMove") {
            val username = intent.getStringExtra("username")
            bookBinding.tvBookSearchText.text = username.toString()
            Log.e("username", username.toString())
            bookBinding.tvBookType.text = intentTypeString.toString()
            bookBinding.tvBookSearchSubText.text = "님, 읽고 싶은 책을 선택해주세요!"
        } else if (intentTypeString == "RecommendationMove") {
            bookBinding.tvBookSearchSubText.text = "님, 추천해주고 싶은 책을 선택해주세요!"
        } else {
            val nickName = intent.getStringExtra("nickName")
            bookBinding.tvBookSearchText.text = nickName.toString()
            bookBinding.tvBookType.text = null
            bookBinding.tvBookSearchSubText.text = "님, 좋아하는 책을 선택해주세요!"
        }

        bookBinding.recyclerView.layoutManager = LinearLayoutManager(this)
        bookAdapter = BookAdapter(bookList, bookBinding.tvBookType.text.toString())
        bookBinding.recyclerView.adapter = bookAdapter

        bookBinding.etBookSearch.textChanges()
            .debounce(500, TimeUnit.MILLISECONDS)
            .subscribeOn(Schedulers.io())
            .subscribe {
                bookSearchManager.searchBooks(bookBinding.etBookSearch.text.toString(), 10, "0qMOlEnC8vFb9mMNLWKU", "pVr1B38BBk") { books ->
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