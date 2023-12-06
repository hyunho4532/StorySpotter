package com.hyun.storyspotter.ui.fragment

import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.hyun.storyspotter.R
import com.hyun.storyspotter.databinding.FragmentBookBinding
import com.hyun.storyspotter.type.BookType
import com.hyun.storyspotter.ui.book.BookActivity

class BookFragment : Fragment() {
    private lateinit var _binding: FragmentBookBinding
    private var isFabOpen = false

    private var bookType: BookType = BookType.BookUnInsert

    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBookBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setFAVClickEvent()
    }

    private fun setFAVClickEvent() {
        _binding.fabMain.setOnClickListener {
            toggleFab()
        }

        _binding.fabCapture.setOnClickListener {
            val intent = Intent(this.context, BookActivity::class.java)
            intent.putExtra("bookType", bookType.toString())
            startActivity(intent)
        }

        _binding.fabShare.setOnClickListener {

        }
    }

    private fun toggleFab() {
        Toast.makeText(this.context, "메인 버튼 클릭!", Toast.LENGTH_SHORT).show()

        if (isFabOpen) {
            ObjectAnimator.ofFloat(_binding.fabShare, "translationY", 0f).apply { start() }
            ObjectAnimator.ofFloat(_binding.fabCapture, "translationY", 0f).apply { start() }
            ObjectAnimator.ofFloat(_binding.fabMain, View.ROTATION, 45f, 0f).apply { start() }
        } else { // 플로팅 액션 버튼 열기 - 닫혀있는 플로팅 버튼 꺼내는 애니메이션
            ObjectAnimator.ofFloat(_binding.fabShare, "translationY", -360f).apply { start() }
            ObjectAnimator.ofFloat(_binding.fabCapture, "translationY", -180f).apply { start() }
            ObjectAnimator.ofFloat(_binding.fabMain, View.ROTATION, 0f, 45f).apply { start() }
        }

        isFabOpen = !isFabOpen
    }
}