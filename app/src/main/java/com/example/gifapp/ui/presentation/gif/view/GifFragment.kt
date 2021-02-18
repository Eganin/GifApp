package com.example.gifapp.ui.presentation.gif.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.gifapp.R
import com.example.gifapp.application.GifApp
import com.example.gifapp.data.model.entity.GifResponse
import com.example.gifapp.databinding.FragmentGifBinding
import com.example.gifapp.ui.presentation.gif.util.FragmentType
import com.example.gifapp.ui.presentation.gif.util.options
import com.example.gifapp.ui.presentation.gif.viewmodel.GifViewModel
import com.example.gifapp.ui.presentation.gif.viewmodel.Pager

class GifFragment : Fragment() {

    private var _binding: FragmentGifBinding? = null
    private val binding get() = _binding!!

    private val viewModel by lazy {
        (requireContext().applicationContext as GifApp).component.getGifViewModel(
            fragment = this@GifFragment
        )
    }

    private var listGif: List<GifResponse> = emptyList()

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
        viewModel.gif.observe(viewLifecycleOwner, this::updateGif)
        viewModel.state.observe(viewLifecycleOwner, this::setState)
        setListenersView()
        when (arguments?.getString(SAVE_TYPE)) {
            HOT -> changeType(view = binding.hotUnderLine, type = HOT)
            TOP -> changeType(view = binding.topUnderLine, type = TOP)
            else -> changeType(view = binding.latestUnderLine, type = LATEST)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
        Pager.page++
    }

    private fun setState(state: GifViewModel.State) =
        when (state) {
            GifViewModel.State.Error -> changeState(stateCondition = true)
            GifViewModel.State.Success -> {
                setLoading(state = false)
                changeState(stateCondition = false)
            }
            GifViewModel.State.Loading -> setLoading(state = true)

            GifViewModel.State.Default -> {
            }
        }

    private fun changeState(stateCondition: Boolean) {
        binding.messageError.isVisible = stateCondition
        binding.imageError.isVisible = stateCondition
        binding.materialGifView.isVisible = !stateCondition
        binding.materialNext.isEnabled=!stateCondition
        binding.materialReset.isEnabled=!stateCondition
    }

    private fun setLoading(state: Boolean) {
        binding.progressBar.isVisible = state
    }

    private fun setListenersView() {
        binding.materialNext.setOnClickListener {
            Log.d("SIZE",listGif.size.toString())
            Pager.count++
            if (Pager.count % 5 == 0) {
                Pager.count = 0
                load()
            } else {
                updateView(gif = listGif[Pager.count])
            }
        }
        binding.materialReset.setOnClickListener {
            if (Pager.count != 0) {
                Pager.count--
                updateView(gif = listGif[Pager.count])
            } else if (Pager.page != 0) {
                Pager.page -= 2
                Pager.count = 4
                load()
            }
        }

        binding.tvLatest.setOnClickListener {
            changeType(view = it, type = LATEST)
        }

        binding.tvHot.setOnClickListener {
            changeType(view = it, type = HOT)
        }

        binding.tvTop.setOnClickListener {
            changeType(view = it, type = TOP)
        }
    }

    private fun changeType(view: View, type: String) {
        val text = view as TextView
        text.setTextColor(resources.getColor(R.color.black))
        arguments = Bundle().apply {
            putString(SAVE_TYPE, type)
        }
        when (type) {
            LATEST -> {
                binding.latestUnderLine.isVisible = true
                binding.hotUnderLine.isVisible = false
                binding.topUnderLine.isVisible = false
                binding.postDescription.isVisible = true
            }
            TOP -> {
                binding.latestUnderLine.isVisible = false
                binding.hotUnderLine.isVisible = false
                binding.topUnderLine.isVisible = true
                binding.postDescription.isVisible = true
            }
            else -> {
                binding.latestUnderLine.isVisible = false
                binding.hotUnderLine.isVisible = true
                binding.topUnderLine.isVisible = false
                binding.postDescription.isVisible = false
            }
        }
        if (Pager.page != -1&&type!=HOT) {
            Pager.page--
        } else {
            Pager.page = -1
        }
        load()
    }

    private fun getType() =
        when {
            binding.hotUnderLine.isVisible -> HOT
            binding.topUnderLine.isVisible -> TOP
            else -> LATEST
        }

    private fun load() {
        Log.d("LOAD",getType())
        when (getType()) {
            HOT -> viewModel.loadGif(type = FragmentType.HOT)
            LATEST -> viewModel.loadGif(type = FragmentType.LATEST)
            TOP -> viewModel.loadGif(type = FragmentType.TOP)
        }
    }


    private fun updateGif(value: List<GifResponse>) {
        listGif = value
        updateView(gif = listGif[Pager.count])
    }

    private fun updateView(gif: GifResponse) {
        Glide.with(this)
            .asGif()
            .apply(options)
            .load(gif.gifURL)
            .into(binding.gifView)

        binding.postDescription.text = gif.description
    }

    companion object {
        private const val LATEST = "LATEST"
        private const val HOT = "HOT"
        private const val TOP = "TOP"
        private const val SAVE_TYPE = "SAVE_TYPE"
    }

}