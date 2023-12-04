package com.hyun.storyspotter.ui.register.finish

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.hyun.storyspotter.R
import com.hyun.storyspotter.databinding.ActivityFinishBinding
import com.hyun.storyspotter.type.ImageType
import com.hyun.storyspotter.ui.book.BookActivity

class FinishActivity : AppCompatActivity() {

    private lateinit var activityFinishBinding: ActivityFinishBinding
    private lateinit var database: FirebaseDatabase
    private lateinit var auth: FirebaseAuth
    private var imageType: ImageType = ImageType.AddImage

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityFinishBinding = DataBindingUtil.setContentView(this, R.layout.activity_finish)

        database = FirebaseDatabase.getInstance()
        auth = FirebaseAuth.getInstance()

        val user = auth.currentUser!!

        val imageUrl = intent.getStringExtra("imageUrl")

        Glide.with(activityFinishBinding.root)
            .load(imageUrl)
            .into(activityFinishBinding.ivWelcomeLikeBook)

        Glide.with(activityFinishBinding.root)
            .load(imageUrl)
            .into(activityFinishBinding.ivWelcomeLikeBookSecond)

        activityFinishBinding.btnBookSearchLikeAdd.setOnClickListener {
            val intent = Intent(this@FinishActivity, BookActivity::class.java)
            intent.putExtra("imageType", imageType)
            startActivity(intent)
        }

        val userReference = database.reference.child("users").child(user.uid)
        userReference.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (snapshot in dataSnapshot.children) {
                        val username = dataSnapshot.child("username").value.toString()
                        val hobby = dataSnapshot.child("hobby").value.toString()

                        activityFinishBinding.tvFinishUser.text = username
                        activityFinishBinding.tvHobbyText.text = hobby
                    }
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // 에러 처리
            }
        })

        activityFinishBinding.btnFinishAuthInsert.setOnClickListener {
            firebaseUpdateAuth(activityFinishBinding.tvFinishUser.text.toString(), imageUrl.toString(), activityFinishBinding.tvHobbyText.text.toString())
        }
    }

    private fun firebaseUpdateAuth(username: String, image: String, hobby: String) {
        val user: FirebaseUser = auth.currentUser!!

        val userUpdates = HashMap<String, Any>()

        userUpdates["username"] = username
        userUpdates["image"] = image
        userUpdates["hobby"] = hobby

        database.reference.child("users").child(user.uid).updateChildren(userUpdates)
            .addOnCompleteListener {
                print("데이터를 수정 완료하였습니다.")
            }
            .addOnFailureListener {
                print("데이터 수정 중 오류가 발생하였습니다.")
            }
    }

}