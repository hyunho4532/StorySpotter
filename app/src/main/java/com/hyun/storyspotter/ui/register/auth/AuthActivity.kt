package com.hyun.storyspotter.ui.register.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.FirebaseDatabase
import com.hyun.storyspotter.R
import com.hyun.storyspotter.databinding.ActivityAuthBinding
import com.hyun.storyspotter.ui.book.BookActivity

class AuthActivity : AppCompatActivity() {

    private lateinit var authBinding: ActivityAuthBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var database: FirebaseDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        authBinding = DataBindingUtil.setContentView(this, R.layout.activity_auth)

        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance()

        val photoUrl = intent.getStringExtra("photoUrl")

        Glide.with(authBinding.root)
            .load(photoUrl)
            .into(authBinding.ivGoogleProfile)

        authBinding.btnAuthInsert.setOnClickListener {
            val intent = Intent(this@AuthActivity, BookActivity::class.java)
            intent.putExtra("nickName", authBinding.usernameTextField.text.toString())
            startActivity(intent)

            firebaseUpdateAuth(authBinding.usernameTextField.text.toString(), authBinding.hobbyTextField.text.toString())
        }
    }

    private fun firebaseUpdateAuth(username: String, hobby: String) {
        val user: FirebaseUser = auth.currentUser!!

        val userUpdates = HashMap<String, Any>()

        userUpdates["username"] = username
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