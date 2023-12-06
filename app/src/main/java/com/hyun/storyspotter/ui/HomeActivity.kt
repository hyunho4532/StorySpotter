package com.hyun.storyspotter.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.database.Transaction
import com.hyun.storyspotter.R
import com.hyun.storyspotter.event.LoadNavigationItemSelectedListener
import com.hyun.storyspotter.ui.fragment.BookFragment
import com.hyun.storyspotter.ui.fragment.HomeFragment
import com.hyun.storyspotter.ui.fragment.ProfileFragment

class HomeActivity : AppCompatActivity() {

    private val fragmentManager: FragmentManager = supportFragmentManager
    private val homeFragment: HomeFragment = HomeFragment()
    private val bookFragment: BookFragment = BookFragment()
    private val profileFragment: ProfileFragment = ProfileFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val bottomNavigationView: BottomNavigationView = findViewById(R.id.menu_bottom_navigation)
        bottomNavigationView.setOnNavigationItemSelectedListener (
            LoadNavigationItemSelectedListener(fragmentManager, homeFragment, bookFragment, profileFragment)
        )

        val transaction: FragmentTransaction = fragmentManager.beginTransaction()
        transaction.replace(R.id.menu_frame_layout, homeFragment).commit()
    }
}