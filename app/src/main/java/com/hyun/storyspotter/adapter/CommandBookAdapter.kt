package com.hyun.storyspotter.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.hyun.storyspotter.R
import com.hyun.storyspotter.databinding.ItemSearchBookBinding
import com.hyun.storyspotter.model.CommandBook

class CommandBookAdapter (
    private val context: Context,
    private val commandBookList: ArrayList<CommandBook>,
) : BaseAdapter() {
    override fun getCount(): Int {
        return commandBookList.size
    }

    override fun getItem(position: Int): Any {
        return commandBookList[position]
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    @SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View = LayoutInflater.from(context).inflate(R.layout.item_recommand_book, null)
        val title = view.findViewById<TextView>(R.id.reCommandBookTitle)

        val book = commandBookList[position]

        title.text = book.title

        return view
    }
}