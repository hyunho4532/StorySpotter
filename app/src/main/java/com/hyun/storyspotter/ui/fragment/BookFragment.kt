package com.hyun.storyspotter.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.hyun.storyspotter.R
import com.hyun.storyspotter.adapter.BookAdapter
import com.hyun.storyspotter.adapter.CommandBookAdapter
import com.hyun.storyspotter.databinding.FragmentBookBinding
import com.hyun.storyspotter.model.BookItem
import com.hyun.storyspotter.model.CommandBook
import com.hyun.storyspotter.type.IntentType
import com.hyun.storyspotter.ui.book.BookActivity
import com.hyun.storyspotter.ui.book.detail.BookDetailActivity

class BookFragment : Fragment() {

    val intentType: IntentType = IntentType.RecommendationMove

    private var _fragmentBookBinding: FragmentBookBinding? = null

    private val fragmentBookBinding get() = _fragmentBookBinding!!

    private lateinit var db: FirebaseFirestore
    private lateinit var auth: FirebaseAuth

    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _fragmentBookBinding = FragmentBookBinding.inflate(inflater, container, false)
        val root: View = _fragmentBookBinding!!.root

        db = FirebaseFirestore.getInstance()
        auth = FirebaseAuth.getInstance()

        recyclerView = _fragmentBookBinding!!.mainMenu

        var commandBookList = arrayListOf<CommandBook>()

        db.collection("books")
            .get()
            .addOnSuccessListener { results ->
                for (document in results) {
                    val title = document["title"]

                    commandBookList.add(CommandBook(title.toString(), "ddd", "dd"))
                }

                val commandBookAdapter = CommandBookAdapter(requireContext(), commandBookList)
                recyclerView.adapter = commandBookAdapter
            }

        return root
    }
}