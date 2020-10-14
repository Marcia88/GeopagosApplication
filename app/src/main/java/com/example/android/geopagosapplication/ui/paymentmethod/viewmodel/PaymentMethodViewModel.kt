package com.example.android.geopagosapplication.ui.paymentmethod.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android.geopagosapplication.model.Payment
import com.example.android.geopagosapplication.model.PaymentMethod
import com.example.android.geopagosapplication.repository.PaymentMethodRepository
import com.example.android.geopagosapplication.utils.NetworkHelper
import com.example.android.geopagosapplication.utils.Resource
import kotlinx.coroutines.launch

class PaymentMethodViewModel(
    private val paymentMethodRepository: PaymentMethodRepository,
    private val networkHelper: NetworkHelper,
    private val payment: Payment
) : ViewModel() {

    private val _paymentMethods = MutableLiveData<Resource<List<PaymentMethod>>>()
    val paymentMethods: LiveData<Resource<List<PaymentMethod
            >>>
        get() = _paymentMethods

    init {
        fetchPaymentMethods()
    }

    private fun fetchPaymentMethods() {
        viewModelScope.launch {
            _paymentMethods.postValue(Resource.loading(null))
            if (networkHelper.isNetworkConnected()) {
                paymentMethodRepository.getPaymentMethods().let {
                    if (it.isSuccessful) {
                        _paymentMethods.postValue(Resource.success(paymentMethodRepository.mapPaymentMethods(it)))
                    } else {
                        _paymentMethods.postValue(Resource.error(it.errorBody().toString(), null))
                    }
                }
            } else {
                _paymentMethods.postValue(Resource.error("No internet connection", null))
            }
        }
    }

    fun paymentMethodSelected(paymentMethod: PaymentMethod) {
        payment.paymentMethod = paymentMethod
    }
}