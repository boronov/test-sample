package ru.appsmile.test.hotel.presentation.common

import android.animation.ObjectAnimator
import android.view.View
import android.widget.EditText
import androidx.core.content.ContextCompat
import androidx.core.widget.doAfterTextChanged
import com.google.android.material.textfield.TextInputLayout
import ru.appsmile.test.hotel.R

fun TextInputLayout.setupValidator(block: (EditText?) -> Boolean, onValid: (EditText?) -> Unit) {

    fun setValidBackground(isValid: Boolean) {
        boxBackgroundColor = if (isValid) {
            ContextCompat.getColor(context, R.color.box_input_default)
        } else {
            ContextCompat.getColor(context, R.color.box_input_error)
        }
    }

    editText?.doAfterTextChanged {
        val isValidInput = block(editText)

        if (isValidInput) setValidBackground(true)

        if (isValidInput) {
            onValid(editText)
        } else {
            onValid(null)
        }
    }

    editText?.setOnFocusChangeListener { view, b ->
        if (!b) {
            val isValidInput = block(editText)
            setValidBackground(isValidInput)
        }
    }
}

fun View.rotateView(duration: Long, from: Float, to: Float) {
    val animator = ObjectAnimator.ofFloat(this, View.ROTATION, from, to)
    animator.duration = duration
    animator.start()
}