package com.mylivn.commons.ui.utils.extensions

import android.content.Context
import com.mylivn.domain.models.base.Failure
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*


fun Failure.getErrorMessage(context: Context): String {
    return when {
        message != null -> {
            message!!
        }
        messageRes != null -> {
            context.getString(this.messageRes!!)
        }
        else -> {
            "Unknown error has occurred"
        }
    }
}


