package com.hyun.storyspotter.service

import com.hyun.storyspotter.model.BookSearchResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

// ( 네이버 책 검색 API ) 통신하기 위한 Retrofit 인터페이스 생성
interface BookSearchService {
    @GET("/v1/search/book.json")
    fun searchBooks (
        @Query("query") query: String,
        @Query("display") display: Int,
        @Header("X-Naver-Client-Id") id: String,
        @Header("X-Naver-Client-Secret") password: String
    ): Call<BookSearchResponse>
}