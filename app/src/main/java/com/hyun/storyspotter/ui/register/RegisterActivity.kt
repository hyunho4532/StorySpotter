package com.hyun.storyspotter.ui.register

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.database.FirebaseDatabase
import com.hyun.storyspotter.R
import com.hyun.storyspotter.databinding.ActivityRegisterBinding
import com.hyun.storyspotter.ui.register.auth.AuthActivity
import com.hyun.storyspotter.viewmodel.GoogleAuthViewModel
import kotlin.collections.HashMap

class RegisterActivity : AppCompatActivity() {
    private lateinit var googleSignInClient: GoogleSignInClient
    private lateinit var registerBinding: ActivityRegisterBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var database: FirebaseDatabase

    private lateinit var googleAuthViewModel: GoogleAuthViewModel

    private val RC_SIGN_IN = 20

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        registerBinding = DataBindingUtil.setContentView(this, R.layout.activity_register)

        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance()

        val googleSignInOptions: GoogleSignInOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail().build()

        googleSignInClient = GoogleSignIn.getClient(this, googleSignInOptions)

        registerBinding.btnGoogle.setOnClickListener {
            googleSignIn()
        }
    }

    private fun googleSignIn() {
        val intent: Intent = googleSignInClient.signInIntent
        startActivityForResult(intent, RC_SIGN_IN)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RC_SIGN_IN) {
            val task: Task<GoogleSignInAccount> = GoogleSignIn.getSignedInAccountFromIntent(data)

            try {
                val account: GoogleSignInAccount = task.getResult(ApiException::class.java)
                firebaseAuth(account.idToken)

            } catch(e: Exception) {
                Log.d("RegisterActivity", e.printStackTrace().toString())
            }
        }
    }

    private fun firebaseAuth(idToken: String?) {
        val credential: AuthCredential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential).addOnCompleteListener {
            if (it.isSuccessful) {
                val user: FirebaseUser = auth.currentUser!!

                val map: HashMap<String, String> = HashMap()

                map["id"] = user.uid
                map["name"] = user.displayName.toString()
                map["profile"] = user.photoUrl.toString()

                database.reference.child("users").child(user.uid).setValue(map)

                val intent = Intent(registerBinding.root.context, AuthActivity::class.java)
                intent.putExtra("photoUrl", user.photoUrl.toString())
                startActivity(intent)
            }
        }
    }
}