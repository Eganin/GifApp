package com.example.gifapp.ui.screens.gif

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.gifapp.R
import com.example.gifapp.ui.presentation.gif.util.Router
import com.example.gifapp.ui.presentation.gif.view.GifFragment
import com.example.gifapp.ui.presentation.gif.viewmodel.Pager

class GifActivity : AppCompatActivity(), Router {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if(savedInstanceState==null) openGifFragment()
    }

    override fun openGifFragment() {
        openNewFragment(fragment = GifFragment(),addToBackStack = true)
    }

    override fun onDestroy() {
        super.onDestroy()
        Pager.page--
    }

    private fun openNewFragment(fragment: Fragment, addToBackStack: Boolean = false) {
        val fragmentTransaction =
                supportFragmentManager.beginTransaction().replace(R.id.main_container, fragment)

        if (addToBackStack) {
            fragmentTransaction.addToBackStack(fragment::class.java.simpleName)
        }

        fragmentTransaction.commit()
    }



}