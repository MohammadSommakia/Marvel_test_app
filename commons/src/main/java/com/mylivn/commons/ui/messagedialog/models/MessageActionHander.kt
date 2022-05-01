package com.mylivn.commons.ui.messagedialog.models

import com.mylivn.commons.ui.messagedialog.MessageDialogFragment


class MessageActionHandler(
    val dialog: MessageDialogFragment,
    val mainAction: (MessageDialogFragment) -> Unit,
    val secondaryAction: ((MessageDialogFragment) -> Unit)?
) {

    fun executeMainAction() {
        mainAction(dialog)
    }

    fun executeSecondaryAction() {
        secondaryAction?.let { it(dialog) }
    }

}