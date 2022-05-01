package com.mylivn.commons.ui.messagedialog

import android.app.Activity
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.mylivn.commons.R
import com.mylivn.commons.ui.messagedialog.models.MessageDialog
import com.mylivn.commons.ui.messagedialog.models.MessageDialogAction
import com.mylivn.commons.ui.messagedialog.models.MessageDialogIcon
import com.mylivn.commons.ui.utils.extensions.getErrorMessage
import com.mylivn.commons.ui.utils.extensions.getMainNavController
import com.mylivn.domain.models.base.Failure

/**
 * This singleton class is responsible for showing the dialog and preserving the buttons listeners.
 */
object MessageDialogHelper {

    internal var mainButtonAction: MessageDialogAction? = null

    internal var secondaryButtonAction: MessageDialogAction? = null

    /**
     * This method should be called when [MessageDialogFragment] is dismissed to prevent memory leaks.
     */
    internal fun clearActions() {
        mainButtonAction = null
        secondaryButtonAction = null
    }

    fun showMessageDialog(
        message: MessageDialog,
        mainAction: MessageDialogAction,
        secondaryAction: MessageDialogAction? = null,
        activity: Activity
    ) {
        mainButtonAction = mainAction
        secondaryButtonAction = secondaryAction

        activity.getMainNavController()
            .navigate(R.id.action_global_messageDialogFragment, bundleOf("message" to message))
    }

    fun showErrorDialog(activity: Activity, content: String, action: MessageDialogAction) {
        val message = MessageDialog(
            icon = MessageDialogIcon.ERROR,
            body = content,
            cancelable = false
        )

        mainButtonAction = action

        val args = bundleOf("message" to message)

        activity.getMainNavController()
            .navigate(R.id.action_global_messageDialogFragment, args)
    }

    fun showErrorDialogFromFailure(fragment: Fragment, failure: Failure) {
        with(fragment) {
            val message = MessageDialog(
                icon = MessageDialogIcon.ERROR,
                body = failure.getErrorMessage(requireContext())
            )

            val mainAction = MessageDialogAction(
                title = getString(R.string.error_dialog_button_ok),
                action = {
                    it.dismiss()
                }
            )

            showMessageDialog(message, mainAction, null, requireActivity())
        }
    }

}