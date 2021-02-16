package com.example.gifapp.ui.screens.gif

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.gifapp.R
import com.example.gifapp.databinding.ActivityMainBinding

class GifActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_main)

    }

    private fun setupView(){
        binding.navView.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.navigation_hot->{}
                R.id.navigation_last->{}
                R.id.navigation_top->{}
                else->false
            }
        }
    }

}