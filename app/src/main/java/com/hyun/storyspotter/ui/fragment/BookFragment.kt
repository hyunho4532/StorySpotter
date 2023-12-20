package com.hyun.storyspotter.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ListView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
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

    private lateinit var unBookMyRef: DatabaseReference
    private lateinit var db: FirebaseFirestore
    private lateinit var auth: FirebaseAuth

    private lateinit var profileImage: String

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

        val commandBookList = arrayListOf<CommandBook>()

        auth = FirebaseAuth.getInstance()

        auth.currentUser?.uid?.let { uid ->
            val firebaseDatabase: FirebaseDatabase = FirebaseDatabase.getInstance()
            unBookMyRef = firebaseDatabase.getReference("users").child(uid)
        }

        unBookMyRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val profile = snapshot.child("profile").getValue(String::class.java)

                profileImage = profile.toString()
            }

            override fun onCancelled(error: DatabaseError) {

            }
        })

        db.collection("books")
            .get()
            .addOnSuccessListener { results ->
                for (document in results) {
                    val title = document["title"]
                    val imageUrl = document["imageUrl"]
                    val author = document["author"]

                    commandBookList.add(CommandBook(title.toString(), imageUrl.toString(), author.toString(), profileImage))
                }

                val commandBookAdapter = CommandBookAdapter(requireContext(), commandBookList)
                recyclerView.adapter = commandBookAdapter
                recyclerView.layoutManager = LinearLayoutManager(requireContext())
            }

        return root
    }

}