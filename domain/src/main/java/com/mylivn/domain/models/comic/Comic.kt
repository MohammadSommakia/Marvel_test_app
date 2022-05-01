package com.mylivn.domain.models.comic


data class Comic(
    val id: Int,
    var characterId: Int,
    val name: String,
    val description: String,
    val imagePath: String,
)