package ru.appsmile.test.hotel.presentation.common

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