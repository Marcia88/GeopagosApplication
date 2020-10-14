package com.example.android.geopagosapplication.ui.amount.viewmodel

import androidx.databinding.Observable
import androidx.databinding.PropertyChangeRegistry
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.android.geopagosapplication.model.Payment


class AmountViewModel(private val payment: Payment) : ViewModel(), Observable {

    private val callbacks: PropertyChangeRegistry by lazy { PropertyChangeRegistry() }
    var amount: Double = 0.0
        set(value) {
            notifyPropertyChanged()
            field = value
        }
    private val _isAmountValid = MutableLiveData<Boolean>()

    val isAmountValid: LiveData<Boolean>
        get() {
            return _isAmountValid
        }

    fun next() {
        _isAmountValid.value = amount >= 1
    }

    fun onAmountEntered() {
        payment.amount = amount
    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback) {
        callbacks.add(callback)
    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
        callbacks.remove(callback)
    }

    private fun notifyPropertyChanged() {
        callbacks.notifyCallbacks(this, 1, null)
    }
}