package com.mylivn.domain.models.series


data class Series(
    val id: Int,
    var characterId: Int,
    val name: String,
    val description: String,
    val imagePath: String,
)