package com.example.gifapp.ui.presentation.gif.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
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
import com.example.gifapp.ui.presentation.gif.viewmodel.Pager

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
        setListenersView()
        load()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun setListenersView() {
        binding.materialNext.setOnClickListener {
            load()
        }
        binding.materialReset.setOnClickListener {
            Pager.count--
            load()
        }

        binding.tvLatest?.setOnClickListener {
            changeType(view = it, type = LATEST)
        }

        binding.tvHot?.setOnClickListener {
            changeType(view = it, type = HOT)
        }

        binding.tvTop?.setOnClickListener {
            changeType(view = it, type = TOP)
        }
    }

    private fun changeType(view: View, type: String) {
        Pager.count=0
        val text = view as TextView
        text.setTextColor(resources.getColor(R.color.black))
        when (type) {
            LATEST -> {
                binding.latestUnderLine?.isVisible = true
                binding.hotUnderLine?.isVisible = false
                binding.topUnderLine?.isVisible = false
            }
            TOP -> {
                binding.latestUnderLine?.isVisible = false
                binding.hotUnderLine?.isVisible = false
                binding.topUnderLine?.isVisible = true
            }
            else -> {
                binding.latestUnderLine?.isVisible = false
                binding.hotUnderLine?.isVisible = true
                binding.topUnderLine?.isVisible = false
            }
        }
        load()
    }

    private fun getType() =
        when {
            binding.latestUnderLine?.isVisible == true -> LATEST
            binding.topUnderLine?.isVisible == true -> TOP
            else -> HOT
        }

    private fun load() {
        when (getType()) {
            HOT -> viewModel.loadGif(type = FragmentType.HOT)
            LATEST -> viewModel.loadGif(type = FragmentType.LATEST)
            TOP -> viewModel.loadGif(type = FragmentType.TOP)
        }
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
        private const val LATEST = "LATEST"
        private const val HOT = "HOT"
        private const val TOP = "TOP"
    }

}