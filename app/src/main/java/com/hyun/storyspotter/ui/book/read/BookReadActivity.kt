package com.hyun.storyspotter.ui.book.read

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.hyun.storyspotter.R
import com.hyun.storyspotter.databinding.ActivityBookReadBinding
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.MaterialCalendarView
import com.prolificinteractive.materialcalendarview.OnRangeSelectedListener

class BookReadActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBookReadBinding

    private lateinit var startDay: String
    private lateinit var endDay: String

    private val TAG = "BookReadActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_book_read)

        val imageUrl = intent.getStringExtra("imageUrl")
        val title = intent.getStringExtra("title")

        Glide.with(binding.root)
            .load(imageUrl)
            .into(binding.ivReadBook)

        binding.tvReadBookTitle.text = title

        binding.calendarview.setOnRangeSelectedListener(object: OnRangeSelectedListener {
            override fun onRangeSelected(widget: MaterialCalendarView, dates: MutableList<CalendarDay>) {
                startDay = dates[0].date.toString();
                endDay = dates.get(dates.size - 1).date.toString()

                Log.v(TAG, "시작일 : $startDay, 종료일 : $endDay")

                binding.tvBookReadStartDate.text = startDay
                binding.tvBookReadEndDate.text = endDay
            }
        })
    }
}