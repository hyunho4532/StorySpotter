package com.hyun.storyspotter.adapter

import android.util.Pair
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DecodeFormat
import com.bumptech.glide.request.RequestOptions
import com.hyun.storyspotter.R
import com.hyun.storyspotter.common.MakeTransitionAnimation
import com.hyun.storyspotter.databinding.ItemSearchBookBinding
import com.hyun.storyspotter.model.BookItem
import java.security.PrivateKey

class BookAdapter(
    private val books: List<BookItem>,
) : RecyclerView.Adapter<BookAdapter.BookViewHolder>() {

    private val transitionAnimation = MakeTransitionAnimation()

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

    inner class BookViewHolder(private val binding: ItemSearchBookBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(book: BookItem) {
            binding.titleTextView.text = book.title
            binding.authorTextView.text = book.author

            val options = RequestOptions()
                .placeholder(R.drawable.ic_launcher_foreground)
                .error(R.drawable.ic_launcher_background)
                .format(DecodeFormat.PREFER_ARGB_8888)
                .encodeQuality(0)

            Glide.with(binding.root)
                .load(book.image)
                .apply(options)
                .into(binding.bookImageView)

            val pair = Pair<View, String>(binding.bookImageView, "imageTran")

            binding.root.setOnClickListener {
                transitionAnimation.moveBookDetail(binding.root.context, book.image, book.title, pair)
            }
        }
    }
}
