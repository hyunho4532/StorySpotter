package com.hyun.storyspotter.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
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
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_search_book, parent, false)
        return BookViewHolder(view)
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        val book = books[position]
        holder.bind(book)
    }

    override fun getItemCount(): Int {
        return books.size
    }

    class BookViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private lateinit var itemSearchBookBinding: ItemSearchBookBinding

        fun bind(book: BookItem) {
            itemSearchBookBinding.titleTextView.text = book.title
            itemSearchBookBinding.authorTextView.text = book.author

            val options = RequestOptions()
                .placeholder(R.drawable.ic_launcher_foreground)
                .error(R.drawable.ic_launcher_background)
                .format(DecodeFormat.PREFER_ARGB_8888) // 이미지 포맷 설정
                .encodeQuality(0)

            Glide.with(itemView)
                .load(book.image)
                .apply(options)
                .into(itemSearchBookBinding.bookImageView)
        }
    }

}