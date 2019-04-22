package com.carfax.application.base.common.utils

import java.math.BigDecimal
import java.text.NumberFormat
import java.util.*

class CurrencyUtils {

    private var locale = Locale("pt", "BR")

    private fun normalizeAmount(amount: Long): Double {
        return (amount.toDouble()/100)
    }

    fun formatAmount(amount: BigDecimal): String {
        return NumberFormat.getCurrencyInstance(locale).format(amount)
    }

    fun getCurrencySymbol(): String {
        val currency = Currency.getInstance(locale)
        return currency.symbol
    }

    private fun getWithoutSymbol(amount: String): String {
        return removeSymbol(amount)
    }

    private fun removeSymbol(amount: String): String {
        return amount.replace("[^0-9.,'\\s]+".toRegex(),"")
    }

    fun normalizeAmountWithoutSymbol(amout: Long): String {
        val amountNormalized = normalizeAmount(amout)
        val amountFormatted = formatAmount(amountNormalized.toBigDecimal())
        return getWithoutSymbol(amountFormatted)
    }

}