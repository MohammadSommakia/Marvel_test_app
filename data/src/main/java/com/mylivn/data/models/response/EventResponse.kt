package com.mylivn.data.models.response


data class EventResponse(
    val offset: Int,
    val limit: Int,
    val total: Int,
    val count: Int,
    val results: List<Comics>
)

data class Events(val id: Int, val title: String, val description: String, val thumbnail: Thumbnail)