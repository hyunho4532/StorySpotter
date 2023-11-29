package com.hyun.storyspotter.common

import android.app.Activity
import android.app.ActivityOptions
import android.content.Context
import android.content.Intent
import androidx.core.content.ContextCompat
import com.hyun.storyspotter.databinding.ItemSearchBookBinding
import com.hyun.storyspotter.model.BookItem
import com.hyun.storyspotter.ui.book.detail.BookDetailActivity

class MakeTransitionAnimation {

    fun moveBookDetail(context: Context, book: BookItem) {
        val intent = Intent(context, BookDetailActivity::class.java)

        val options = ActivityOptions.makeSceneTransitionAnimation(context as Activity)
        context.startActivity(intent, options.toBundle())
    }
}