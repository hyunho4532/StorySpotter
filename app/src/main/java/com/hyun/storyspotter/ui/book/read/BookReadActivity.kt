package com.hyun.storyspotter.ui.book.read

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.database
import com.hyun.storyspotter.R
import com.hyun.storyspotter.databinding.ActivityBookReadBinding
import com.hyun.storyspotter.ui.HomeActivity
import com.hyun.storyspotter.ui.fragment.ProfileFragment
import com.hyun.storyspotter.ui.main.MainActivity
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.MaterialCalendarView
import com.prolificinteractive.materialcalendarview.OnRangeSelectedListener

class BookReadActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBookReadBinding

    private lateinit var startDay: String
    private lateinit var endDay: String

    private lateinit var database: DatabaseReference
    private lateinit var auth: FirebaseAuth

    private val TAG = "BookReadActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_book_read)

        database = Firebase.database.reference
        auth = FirebaseAuth.getInstance()

        val imageUrl = intent.getStringExtra("imageUrl")
        val title = intent.getStringExtra("title")
        val author = intent.getStringExtra("author")

        Glide.with(binding.root)
            .load(imageUrl)
            .into(binding.ivReadBook)

        binding.tvReadBookTitle.text = title

        binding.calendarview.setOnRangeSelectedListener { _, dates ->
            startDay = dates[0].date.toString();
            endDay = dates.get(dates.size - 1).date.toString()

            Log.v(TAG, "시작일 : $startDay, 종료일 : $endDay")

            binding.tvBookReadStartDate.text = startDay
            binding.tvBookReadEndDate.text = endDay

            val bookInfo = mapOf(
                "imageUrl" to imageUrl,
                "title" to binding.tvReadBookTitle.text.toString(),
                "author" to author,
                "startDay" to startDay,
                "endDay" to endDay
            )

            binding.btnBookReadInsert.setOnClickListener {
                database.child("books").child(auth.currentUser!!.uid).child("read").setValue(bookInfo)

                val intent = Intent(this@BookReadActivity, HomeActivity::class.java)
                startActivity(intent)
            }
        }
    }
}