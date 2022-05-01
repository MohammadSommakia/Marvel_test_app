package com.mylivn.commons.ui.messagedialog.models

import com.mylivn.commons.ui.messagedialog.MessageDialogFragment
import java.io.Serializable

data class MessageDialogAction(
    val title: String,
    val action: (dialog: MessageDialogFragment) -> Unit
) : Serializable