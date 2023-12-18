package com.hyun.storyspotter.util

import android.annotation.SuppressLint
import android.content.Context
import android.view.View
import android.widget.TextView
import com.bumptech.glide.Glide
import com.hyun.storyspotter.R

class GetToLet {

    fun getLetGlideImageSource(requireContext: Context, profile: String, view: View) {
        Glide.with(requireContext)
            .load(profile)
            .into(view.findViewById(R.id.profile_image))
    }

    fun getLetUsernameAndHobby(username: String?, hobby: String, view: View) {
        val hobbyText = view.findViewById<TextView>(R.id.tv_welcome_name_hobby)
        "${username}님의 취미는 $hobby 입니다.".also { hobby ->
            run {
                hobbyText.text = hobby
            }
        }
    }

    fun getLetFavorite(username: String?, view: View) {
        val favoriteText = view.findViewById<TextView>(R.id.tv_book_favorite_text)
        "${username}님이 좋아하는 책은?".also { username ->
            run {
                favoriteText.text = username
            }
        }
    }
}