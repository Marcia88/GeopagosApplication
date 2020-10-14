package com.example.android.geopagosapplication.utils

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter
import androidx.databinding.InverseBindingListener
import androidx.databinding.InverseMethod

object Converter {
    const val CURRENCY_SYMBOL = "$"
    private const val INIT_DOUBLE = 0.0

    @InverseMethod("currencyToString")
    @JvmStatic
    fun stringToCurrencyFormat(amount: Double): String {
        return CURRENCY_SYMBOL + amount
    }

    @JvmStatic
    fun currencyToString(amount: String): Double {
        val value = amount.removePrefix(CURRENCY_SYMBOL)
        return if (value.isEmpty()) INIT_DOUBLE else value.toDouble()
    }
}

object ConverterBindingAdapters {
    @BindingAdapter("amount")
    @JvmStatic
    fun setAmount(view: EditText, value: String) {
        view.setText(value)
    }

    @InverseBindingAdapter(attribute = "amount")
    @JvmStatic
    fun getAmount(editText: EditText): String {
        return editText.text.toString()
    }

    @BindingAdapter("amountAttrChanged")
    @JvmStatic
    fun setListener(view: EditText, listener: InverseBindingListener?) {
        view.addTextChangedListener(
            object : TextWatcher {
                override fun afterTextChanged(s: Editable?) {
                    //does not apply
                }

                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                    //does not apply
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    val length = s?.length ?: 0
                    if (length == 0) {
                        setAmount(view, Converter.CURRENCY_SYMBOL)
                        view.setSelection(1)
                    } else {
                        listener?.onChange()
                    }
                }
            })
    }
}