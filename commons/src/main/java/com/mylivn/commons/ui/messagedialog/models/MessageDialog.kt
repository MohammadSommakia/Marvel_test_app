package com.mylivn.commons.ui.messagedialog.models

import android.view.Gravity
import java.io.Serializable

data class MessageDialog(
    val icon: MessageDialogIcon = MessageDialogIcon.NONE,
    val title: String? = null,
    val body: String? = null,
    val cancelable: Boolean = true,
    val bodyTextGravity: Int = Gravity.CENTER
) : Serializable

