package com.hyun.storyspotter.ui.book.detail

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.hyun.storyspotter.R
import com.hyun.storyspotter.databinding.ActivityBookDetailBinding
import com.hyun.storyspotter.type.IntentType
import com.hyun.storyspotter.ui.HomeActivity
import com.hyun.storyspotter.ui.book.read.BookReadActivity
import com.hyun.storyspotter.ui.register.finish.FinishActivity


class BookDetailActivity : AppCompatActivity() {

    private lateinit var activityBookDetailBinding: ActivityBookDetailBinding

    val db = FirebaseFirestore.getInstance()
    val auth = FirebaseAuth.getInstance()

    private lateinit var image: String
    private lateinit var title: String
    private lateinit var author: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityBookDetailBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_book_detail)

        image = intent.getStringExtra("image").toString()
        title = intent.getStringExtra("title").toString()
        author = intent.getStringExtra("author").toString()
        val description = intent.getStringExtra("description")
        val publisher = intent.getStringExtra("publisher")

        val intentType = intent.getStringExtra("intentType")

        loadBindingFromGetText(title, author, description, publisher)

        Glide.with(activityBookDetailBinding.root)
            .load(image)
            .into(activityBookDetailBinding.ivDetailBook)

        if (intentType == IntentType.UnLikeMove.toString()) {
            activityBookDetailBinding.btnBookDetailLike.text = "책 읽기"
        } else if (intentType == IntentType.RecommendationMove.toString()) {
            activityBookDetailBinding.btnBookDetailLike.text = "책 추천하기"
        } else {
            activityBookDetailBinding.btnBookDetailLike.text = "책 등록하기"
        }

        activityBookDetailBinding.btnBookDetailLike.setOnClickListener {
            if (intentType == IntentType.UnLikeMove.toString()) {
                val intent = Intent(this@BookDetailActivity, BookReadActivity::class.java)
                intent.putExtra("imageUrl", image)
                intent.putExtra("title", title)
                intent.putExtra("author", author)
                startActivity(intent)
            } else if (intentType == IntentType.RecommendationMove.toString()) {
                val intent = Intent(this@BookDetailActivity, HomeActivity::class.java)
                Toast.makeText(this, "다른 사람들에게 책 추천을 했습니다.", Toast.LENGTH_SHORT).show()
                addFirebaseStoreFromBookReCommandText()
                startActivity(intent)
            } else {
                val intent = Intent(this@BookDetailActivity, FinishActivity::class.java)
                intent.putExtra("imageUrl", image)
                intent.putExtra("title", title)
                startActivity(intent)
            }
        }
    }

    private fun loadBindingFromGetText (
        title: String?,
        author: String?,
        description: String?,
        publisher: String?
    ) {
        activityBookDetailBinding.tvDetailBook.text = title
        activityBookDetailBinding.tvDetailBookAuthor.text = author
        activityBookDetailBinding.tvDetailBookDescription.text = description
        activityBookDetailBinding.tvDetailBookPublisher.text = publisher
    }

    private fun addFirebaseStoreFromBookReCommandText() {
        val books: MutableMap<String, Any> = HashMap()
        books["imageUrl"] = image
        books["title"] = title
        books["author"] = author

        db.collection("books")
            .document(auth.currentUser!!.uid.toString())
            .set(books)
            .addOnSuccessListener {

            }
            .addOnFailureListener {

            }
    }
}