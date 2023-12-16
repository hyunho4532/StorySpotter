package com.hyun.storyspotter.manager

import android.util.Log
import com.hyun.storyspotter.model.BookItem
import com.hyun.storyspotter.model.BookSearchResponse
import com.hyun.storyspotter.service.BookSearchService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class BookSearchManager {
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://openapi.naver.com")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val service = retrofit.create(BookSearchService::class.java)

    fun searchBooks(query: String, displayCount: Int, id: String, password: String, callback: (List<BookItem>?) -> Unit) {
        service.searchBooks(query, displayCount, id, password).enqueue(object: Callback<BookSearchResponse> {
            override fun onResponse(call: Call<BookSearchResponse>, response: Response<BookSearchResponse>) {
                if (response.isSuccessful) {
                    val bookResponse = response.body()
                    Log.d("BookSearchManager -> ", bookResponse.toString())
                    callback(bookResponse?.items)
                } else {
                    callback(null)
                }
            }

            override fun onFailure(call: Call<BookSearchResponse>, t: Throwable) {
                callback(null)
            }
        })
    }
}