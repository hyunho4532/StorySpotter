package com.hyun.storyspotter.ui.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.hyun.storyspotter.R
import com.hyun.storyspotter.type.IntentType
import com.hyun.storyspotter.ui.book.BookActivity
import com.hyun.storyspotter.util.GetToLet

class ProfileFragment : Fragment() {

    private lateinit var auth: FirebaseAuth
    private lateinit var unBookMyRef: DatabaseReference
    private lateinit var bookReadMyRef: DatabaseReference

    private val intentType: IntentType = IntentType.UnLikeMove

    private val getToLet: GetToLet = GetToLet()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view: View = inflater.inflate(R.layout.fragment_profile, container, false)

        val bookReadStartDay = view.findViewById<TextView>(R.id.tv_book_result_read_start_date)
        val bookReadEndDay = view.findViewById<TextView>(R.id.tv_book_result_read_end_date)

        auth = FirebaseAuth.getInstance()

        auth.currentUser?.uid?.let { uid ->
            val firebaseDatabase: FirebaseDatabase = FirebaseDatabase.getInstance()
            unBookMyRef = firebaseDatabase.getReference("users").child(uid)
        }

        auth.currentUser?.uid?.let { uid ->
            val firebaseDatabase: FirebaseDatabase = FirebaseDatabase.getInstance()
            bookReadMyRef = firebaseDatabase.getReference("books").child(uid).child("read")
        }

        bookReadMyRef.addValueEventListener(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val imageUrl = snapshot.child("imageUrl").getValue(String::class.java)
                val startDay = snapshot.child("startDay").getValue(String::class.java)
                val endDay = snapshot.child("endDay").getValue(String::class.java)

                imageUrl?.let {
                    Glide.with(requireContext())
                        .load(imageUrl)
                        .into(view.findViewById(R.id.read_book_image))
                }

                startDay?.let {
                    bookReadStartDay.text = startDay.toString()
                }

                endDay?.let {
                    bookReadEndDay.text = endDay.toString()
                }
            }

            override fun onCancelled(error: DatabaseError) {

            }
        })

        unBookMyRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val profile = snapshot.child("profile").getValue(String::class.java)
                val hobby = snapshot.child("hobby").getValue(String::class.java)
                val username = snapshot.child("username").getValue(String::class.java)

                profile?.let {
                    getToLet.getLetGlideImageSource(requireContext(), profile, view)
                }


                hobby?.let {
                    getToLet.getLetUsernameAndHobby(username, hobby, view)
                }

                val btnReadBookInsert: Button = view.findViewById(R.id.btn_read_book_insert)

                btnReadBookInsert.setOnClickListener {
                    val intent = Intent(requireContext(), BookActivity::class.java)
                    intent.putExtra("intentType", intentType.toString())
                    intent.putExtra("username", username.toString())
                    startActivity(intent)
                }
            }

            override fun onCancelled(error: DatabaseError) {

            }
        })

        return view
    }
}