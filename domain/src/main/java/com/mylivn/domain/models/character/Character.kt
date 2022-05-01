package com.mylivn.domain.models.character

data class Character(
    val id: Int,
    val name: String,
    val description: String,
    val imagePath: String,
) {
    var isSelected = false
}

