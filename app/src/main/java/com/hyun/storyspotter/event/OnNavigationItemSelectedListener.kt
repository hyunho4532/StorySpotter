package com.hyun.storyspotter.event

import android.view.MenuItem
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.hyun.storyspotter.R
import com.hyun.storyspotter.ui.fragment.BookFragment
import com.hyun.storyspotter.ui.fragment.HomeFragment
import com.hyun.storyspotter.ui.fragment.ProfileFragment

class OnNavigationItemSelectedListener (
    private val fragmentManager: FragmentManager,
    private val homeFragment: HomeFragment,
    private val bookFragment: BookFragment,
    private val profileFragment: ProfileFragment,
) : BottomNavigationView.OnNavigationItemSelectedListener {

    override fun onNavigationItemSelected(menuItem: MenuItem): Boolean {
        val transaction: FragmentTransaction = fragmentManager.beginTransaction()

        when (menuItem.itemId) {
            R.id.action_home -> transaction.replace(R.id.menu_frame_layout, homeFragment).commitAllowingStateLoss()
            R.id.action_book -> transaction.replace(R.id.menu_frame_layout, bookFragment).commitAllowingStateLoss()
            R.id.action_profile -> transaction.replace(R.id.menu_frame_layout, profileFragment).commitAllowingStateLoss()
        }

        return true
    }

}