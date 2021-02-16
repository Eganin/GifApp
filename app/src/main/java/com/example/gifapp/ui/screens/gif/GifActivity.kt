package com.example.gifapp.ui.screens.gif

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.gifapp.R
import com.example.gifapp.databinding.ActivityMainBinding
import com.example.gifapp.ui.presentation.gif.util.FragmentType
import com.example.gifapp.ui.presentation.gif.util.Router
import com.example.gifapp.ui.presentation.gif.view.GifFragment

class GifActivity : AppCompatActivity(), Router {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_main)
        setupView()
    }

    override fun openGifFragment() {
        TODO("Not yet implemented")
    }

    private fun openFragment(fragment: Fragment,addToBackStack : Boolean= true) {
        val transaction =
                supportFragmentManager.beginTransaction().replace(R.id.nav_host_fragment, fragment)

        if(addToBackStack) transaction.addToBackStack(fragment::class.java.simpleName)

        transaction.commit()
    }

    private fun setupView() {
        binding.navView.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.navigation_hot -> {
                    openFragment(fragment = GifFragment.newInstance(type=FragmentType.HOT))
                    true
                }
                R.id.navigation_last -> {
                    openFragment(fragment = GifFragment.newInstance(type=FragmentType.LATEST))
                    true
                }
                R.id.navigation_top -> {
                    openFragment(fragment = GifFragment.newInstance(type=FragmentType.TOP))
                    true
                }
                else -> false
            }
        }
    }

}