package com.example.gifapp.ui.presentation.gif.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.example.gifapp.R
import com.example.gifapp.application.GifApp
import com.example.gifapp.data.model.entity.GifResponse
import com.example.gifapp.databinding.FragmentGifBinding
import com.example.gifapp.ui.presentation.gif.util.FragmentType
import com.example.gifapp.ui.presentation.gif.util.options

class GifFragment : Fragment() {

    private var _binding: FragmentGifBinding? = null
    private val binding get() = _binding!!

    private val viewModel by lazy {
        (requireContext().applicationContext as GifApp).component.getGifViewModel(
            fragment = this@GifFragment
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentGifBinding.inflate(inflater, container, false)
        val view = _binding?.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.gif.observe(viewLifecycleOwner, { updateGifView(value = it) })
        viewModel.state.observe(viewLifecycleOwner, { setStateProgressBar(state = it) })
        load()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun load() {
        when (arguments?.getString(SAVE_TYPE_FRAGMENT)) {
            "HOT" -> viewModel.loadGif(type = FragmentType.HOT)
            "LATEST" -> viewModel.loadGif(type = FragmentType.LATEST)
            "TOP" -> viewModel.loadGif(type = FragmentType.TOP)
        }
    }

    private fun setStateProgressBar(state: Boolean) {
        binding.progressBar.isVisible = state
    }

    private fun updateGifView(value: GifResponse) {
        Log.d("AAA", value.gifURL.toString())
        Glide.with(this)
            .asGif()
            .apply(options)
            .load(value.gifURL)
            .into(binding.gifView)


    }


    companion object {

        private const val SAVE_TYPE_FRAGMENT = "SAVE_TYPE_FRAGMENT"

        fun newInstance(type: FragmentType): GifFragment =
            GifFragment().apply {
                arguments = Bundle().apply { putString(SAVE_TYPE_FRAGMENT, type.value) }
            }

    }
}