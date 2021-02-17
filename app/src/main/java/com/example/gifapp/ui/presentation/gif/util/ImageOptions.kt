package com.example.gifapp.ui.presentation.gif.util

import com.bumptech.glide.request.RequestOptions
import com.example.gifapp.R

internal  val options = RequestOptions()
    .placeholder(R.drawable.ic_baseline_crop_original_24)
    .fallback(R.drawable.ic_baseline_crop_original_24)