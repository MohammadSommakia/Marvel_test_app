package com.mylivn.domain.models.story

data class Story(
    val id: Int,
    var characterId: Int,
    val name: String,
    val description: String,
    val imagePath: String,
)