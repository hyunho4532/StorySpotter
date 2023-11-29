package com.hyun.storyspotter.common

import android.app.Activity
import android.app.ActivityOptions
import android.content.Context
import android.content.Intent
import android.util.Pair
import android.view.View
import com.hyun.storyspotter.model.BookItem
import com.hyun.storyspotter.ui.book.detail.BookDetailActivity

class MakeTransitionAnimation {

    fun moveBookDetail(context: Context, image: String, title: String, pair: Pair<View, String>) {
        val intent = Intent(context, BookDetailActivity::class.java)
        intent.putExtra("image", image)
        intent.putExtra("title", title)

        val options = ActivityOptions.makeSceneTransitionAnimation (
            context as Activity,
            pair
        )

        context.startActivity(intent, options.toBundle())
    }
}