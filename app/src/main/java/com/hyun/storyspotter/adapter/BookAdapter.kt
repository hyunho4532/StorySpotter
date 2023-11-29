package com.hyun.storyspotter.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DecodeFormat
import com.bumptech.glide.request.RequestOptions
import com.hyun.storyspotter.R
import com.hyun.storyspotter.databinding.ItemSearchBookBinding
import com.hyun.storyspotter.model.BookItem
import com.hyun.storyspotter.ui.book.detail.BookDetailActivity

class BookAdapter(private val books: List<BookItem>) : RecyclerView.Adapter<BookAdapter.BookViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val binding = ItemSearchBookBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BookViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        val book = books[position]
        holder.bind(book)
    }

    override fun getItemCount(): Int {
        return books.size
    }

    class BookViewHolder(private val binding: ItemSearchBookBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(book: BookItem) {
            binding.titleTextView.text = book.title
            binding.authorTextView.text = book.author

            val options = RequestOptions()
                .placeholder(R.drawable.ic_launcher_foreground)
                .error(R.drawable.ic_launcher_background)
                .format(DecodeFormat.PREFER_ARGB_8888) // 이미지 포맷 설정
                .encodeQuality(0)

            Glide.with(binding.root)
                .load(book.image)
                .apply(options)
                .into(binding.bookImageView)

            binding.root.setOnClickListener {
                val intent = Intent(binding.root.context, BookDetailActivity::class.java)
                ContextCompat.startActivity(binding.root.context, intent, null)
            }
        }
    }
}