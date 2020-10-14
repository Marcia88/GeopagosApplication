package com.example.android.geopagosapplication.ui.installment.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android.geopagosapplication.model.Payment
import com.example.android.geopagosapplication.repository.InstallmentRepository
import com.example.android.geopagosapplication.utils.NetworkHelper
import com.example.android.geopagosapplication.utils.Resource
import kotlinx.coroutines.launch

class InstallmentViewModel(
    private val installmentRepository: InstallmentRepository,
    private val networkHelper: NetworkHelper,
    private val payment: Payment
) : ViewModel() {

    private val _installments = MutableLiveData<Resource<Boolean>>()

    val installments: LiveData<Resource<Boolean>>
        get() = _installments

    val amount = payment.amount.toString()
    val cardIssue = payment.cardIssue?.name
    val paymentMethod = payment.paymentMethod?.name

    fun pay() {
        viewModelScope.launch {
            _installments.postValue(Resource.loading(null))
            if (networkHelper.isNetworkConnected()) {
                payment.paymentMethod?.id?.let { paymentMethodId ->
                    payment.cardIssue?.id?.let { cardIssueId ->
                        installmentRepository.getInstallments(
                            paymentMethodId,
                            payment.amount,
                            cardIssueId
                        )
                            .let {
                                if (it.isSuccessful) {
                                    _installments.postValue(Resource.success(true))
                                } else {
                                    _installments.postValue(
                                        Resource.error(
                                            it.errorBody().toString(),
                                            null
                                        )
                                    )
                                }
                            }
                    }
                }
            } else {
                _installments.postValue(Resource.error("No internet connection", null))
            }
        }
    }
}