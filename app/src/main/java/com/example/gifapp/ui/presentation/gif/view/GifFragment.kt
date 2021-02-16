package com.example.gifapp.ui.presentation.gif.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.gifapp.R
import com.example.gifapp.application.GifApp
import com.example.gifapp.ui.presentation.gif.util.FragmentType

class GifFragment : Fragment(R.layout.fragment_gif) {

    private val viewModel by lazy {
        (requireContext().applicationContext as GifApp).component.getGifViewModel(
            fragment = this@GifFragment
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.gif.observe()
        viewModel.state.observe()
    }

    companion object {

        private const val SAVE_TYPE_FRAGMENT = "SAVE_TYPE_FRAGMENT"

        fun newInstance(type: FragmentType): GifFragment =
            GifFragment().apply {
                arguments = Bundle().apply { putString(SAVE_TYPE_FRAGMENT, type.value) }
            }

    }
}