package com.hyun.storyspotter.ui.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.hyun.storyspotter.R
import com.hyun.storyspotter.ui.book.BookActivity
import com.hyun.storyspotter.ui.book.detail.BookDetailActivity

class BookFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view: View = inflater.inflate(R.layout.fragment_book, container, false)

        val floatingActionButton: FloatingActionButton = view.findViewById(R.id.floatingActionButton)

        floatingActionButton.setOnClickListener {
            val intent = Intent(requireContext(), BookActivity::class.java)
            startActivity(intent)
        }

        return view
    }
}