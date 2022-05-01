package com.mylivn.domain.models.event

data class Event(
    val id: Int,
    var characterId: Int,
    val name: String,
    val description: String,
    val imagePath: String,
)