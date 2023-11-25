package ru.appsmile.test.hotel.presentation.common

import android.animation.ObjectAnimator
import android.view.View
import android.widget.EditText
import androidx.core.content.ContextCompat
import androidx.core.widget.doAfterTextChanged
import com.google.android.material.textfield.TextInputLayout
import ru.appsmile.test.hotel.R
import java.math.RoundingMode
import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.Locale

fun Double.toCurrencyFormat(): String {
    val nf = NumberFormat.getCurrencyInstance(Locale("ru")) as DecimalFormat
    val symbols = nf.decimalFormatSymbols.apply {
        currencySymbol = ""
        monetaryDecimalSeparator = ','
        groupingSeparator = ' '
    }

    nf.minimumFractionDigits = 0
    nf.maximumFractionDigits = 0

    nf.isGroupingUsed = true
    nf.groupingSize = 3
    nf.maximumFractionDigits = 2
    nf.roundingMode = RoundingMode.FLOOR
    nf.decimalFormatSymbols = symbols

    return nf.format(this).trim()
}

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