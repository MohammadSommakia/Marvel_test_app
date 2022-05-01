package com.mylivn.data.models

import com.google.gson.annotations.SerializedName

data class MarvelResponse<T>(
    @SerializedName("code")
    val code: Int?,
    @SerializedName("status")
    val status: String?,
    @SerializedName("data")
    val data: T?
)
