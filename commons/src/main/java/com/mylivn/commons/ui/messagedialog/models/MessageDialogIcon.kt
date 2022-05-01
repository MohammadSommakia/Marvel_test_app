package com.mylivn.commons.ui.messagedialog.models

import com.mylivn.commons.R
import java.io.Serializable

enum class MessageDialogIcon : Serializable {
    ERROR, NONE
}

fun MessageDialogIcon.getDrawableResource(): Int {
    return when (this) {
        MessageDialogIcon.ERROR -> {
            R.drawable.ic_error
        }
        MessageDialogIcon.NONE -> {
            -1
        }
    }
}