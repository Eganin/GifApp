package com.example.gifapp.data.model.entity

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable


@Serializable
data class Response (
    @SerializedName("result")
    @Expose
    var result: List<GifResponse>? = null,

    @SerializedName("totalCount")
    @Expose
    var totalCount: Int? = null
)