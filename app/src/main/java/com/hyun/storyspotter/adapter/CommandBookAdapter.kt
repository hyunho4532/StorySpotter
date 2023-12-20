package com.hyun.storyspotter.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.hyun.storyspotter.R
import com.hyun.storyspotter.model.CommandBook

class CommandBookAdapter (
    private val context: Context,
    private val commandBookList: ArrayList<CommandBook>,
) : RecyclerView.Adapter<CommandBookAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_recommand_book, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val book = commandBookList[position]

        holder.reCommandBookTitle.text = book.title

        Glide.with(context)
            .load(book.imageUrl)
            .into(holder.reCommandBookImage)

        Glide.with(context)
            .load(book.profileUrl)
            .into(holder.reCommandBookGoogleProfile)

        Log.d("CommandBookAdapter", book.profileUrl)
    }


    override fun getItemCount(): Int {
        return commandBookList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val reCommandBookTitle: TextView = itemView.findViewById(R.id.reCommandBookTitle)
        val reCommandBookImage: ImageView = itemView.findViewById(R.id.reCommandBookImage)
        val reCommandBookGoogleProfile: ImageView = itemView.findViewById(R.id.reCommandBookGoogleProfile)
    }
}