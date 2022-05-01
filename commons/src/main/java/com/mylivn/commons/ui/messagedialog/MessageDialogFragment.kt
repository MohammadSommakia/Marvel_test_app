package com.mylivn.commons.ui.messagedialog

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.navArgs
import com.mylivn.commons.R
import com.mylivn.commons.databinding.FragmentLocalMessageDialogBinding
import com.mylivn.commons.ui.messagedialog.models.MessageActionHandler
import com.mylivn.commons.ui.messagedialog.models.getDrawableResource

class MessageDialogFragment : DialogFragment() {

    private lateinit var binding: FragmentLocalMessageDialogBinding
    private val args: MessageDialogFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NO_TITLE, R.style.Theme_Mylivn_DialogStyle)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLocalMessageDialogBinding.inflate(inflater, container, false)

        binding.message = args.message
        binding.mainAction = MessageDialogHelper.mainButtonAction
        binding.secondaryAction = MessageDialogHelper.secondaryButtonAction
        binding.iconResource = args.message.icon.getDrawableResource()
        binding.actionHandler = MessageActionHandler(
            dialog = this,
            mainAction = MessageDialogHelper.mainButtonAction!!.action,
            secondaryAction = MessageDialogHelper.secondaryButtonAction?.action
        )
        isCancelable = args.message.cancelable
        return binding.root
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
        MessageDialogHelper.clearActions()
    }
}