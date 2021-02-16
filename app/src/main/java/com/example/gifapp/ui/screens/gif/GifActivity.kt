package com.example.gifapp.ui.screens.gif

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.gifapp.R
import com.example.gifapp.ui.presentation.gif.util.FragmentType
import com.example.gifapp.ui.presentation.gif.util.Router
import com.example.gifapp.ui.presentation.gif.view.GifFragment

class GifActivity : AppCompatActivity(), Router {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupView()
    }

    override fun openGifFragment() {
        TODO("Not yet implemented")
    }

    private fun openNewFragment(fragment: Fragment, addToBackStack: Boolean = true) {
        val fragmentTransaction =
                supportFragmentManager.beginTransaction().replace(R.id.main_container, fragment)

        if (addToBackStack) {
            fragmentTransaction.addToBackStack(fragment::class.java.simpleName)
        }

        fragmentTransaction.commit()
    }

    private fun setupView() {
        findViewById<BottomNavigationView>(R.id.nav_view).setOnNavigationItemSelectedListener{
            when (it.itemId) {
                R.id.navigation_hot -> {
                    openNewFragment(fragment = GifFragment.newInstance(type=FragmentType.HOT))
                    true
                }
                R.id.navigation_last -> {
                    openNewFragment(fragment = GifFragment.newInstance(type=FragmentType.LATEST))
                    true
                }
                R.id.navigation_top -> {
                    openNewFragment(fragment = GifFragment.newInstance(type=FragmentType.TOP))
                    true
                }
                else -> false
            }
        }
    }

}