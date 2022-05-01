package com.mylivn.data.models.response

data class SeriesResponse(
    val offset: Int,
    val limit: Int,
    val total: Int,
    val count: Int,
    val results: List<Comics>
)

data class Series(
    val id: Int,
    val title: String,
    val description: String,
    val thumbnail: Thumbnail
)