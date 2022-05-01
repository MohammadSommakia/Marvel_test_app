package com.mylivn.commons.ui.utils

import android.net.Uri
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import androidx.transition.TransitionManager
import com.mylivn.commons.ui.utils.extensions.getErrorMessage
import com.mylivn.domain.models.base.Failure
import kotlin.math.roundToInt


@BindingAdapter("android:textString")
fun TextView.setStringResource(text: String) {
    this.text = text
}

@BindingAdapter("android:text_string_resource")
fun TextView.setTextResource(resourceName: String?) {
    resourceName?.let {
        if (it.isNotEmpty()) {
            val resource =
                context.resources.getIdentifier(resourceName, "string", context.packageName)
            if (resource != 0)
                this.setText(resource)
        }
    }
}

@BindingAdapter("src")
fun ImageView.setDrawableByName(name: String?) {
    name?.let {
        val context = this.context
        val packageName = context.packageName
        val uri = Uri.parse("android.resource://$packageName/drawable/$name")
        if (uri == null) {
            this.setImageURI(null)
        } else {
            this.setImageURI(uri)
        }
    }
}

@BindingAdapter("src")
fun ImageView.setDrawableById(id: Int?) {
    id?.let {
        if (it != -1) {
            this.setImageDrawable(ContextCompat.getDrawable(context, id))
        }
    }
}

@BindingAdapter("animatedVisibility")
fun View.setAnimatedVisibility(visibility: Int) {
    TransitionManager.beginDelayedTransition(this.rootView as ViewGroup)
    this.visibility = visibility
}

@BindingAdapter("navigationOnClick")
fun Toolbar.setNavigationClickListener(listener: () -> Unit) {
    this.setNavigationOnClickListener {
        listener()
    }
}

@BindingAdapter("setNavigationIcon")
fun Toolbar.setNavigationIcon(drawableRes: Int?) {
    this.navigationIcon =
        if (drawableRes == null) null else ContextCompat.getDrawable(this.context, drawableRes)
}

@BindingAdapter("textWithArgs", "arg1", "arg2", "arg3", requireAll = false)
fun TextView.setTextWithArgs(textResName: String?, arg1: Any?, arg2: Any?, arg3: Any?) {
    textResName?.let {
        val resourceId = context.resources.getIdentifier(it, "string", context.packageName)
        this.text = context.getString(resourceId, arg1, arg2, arg3)
    }
}

@BindingAdapter("textFromFailure")
fun TextView.setTextFromFailure(failure: Failure?) {
    failure?.let {
        this.text = failure.getErrorMessage(context)
    }
}




@BindingAdapter("onCheckedListener")
fun CheckBox.setOnCheckedListener(listener: ((checked: Boolean) -> Unit)?) {
    listener?.let {
        setOnCheckedChangeListener { _, checked ->
            it.invoke(checked)
        }
    }
}

@BindingAdapter("marginEnd")
fun View.setMarginEnd(marginEnd: Float?) {
    marginEnd?.let {
        val params = (layoutParams as ViewGroup.MarginLayoutParams)
        params.marginEnd = marginEnd.roundToInt()
        layoutParams = params
    }
}