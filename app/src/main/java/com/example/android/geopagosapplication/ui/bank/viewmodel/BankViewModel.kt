package com.example.android.geopagosapplication.ui.bank.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android.geopagosapplication.model.CardIssue
import com.example.android.geopagosapplication.model.Payment
import com.example.android.geopagosapplication.repository.BankRepository
import com.example.android.geopagosapplication.utils.NetworkHelper
import com.example.android.geopagosapplication.utils.Resource
import kotlinx.coroutines.launch

class BankViewModel(
    private val bankRepository: BankRepository,
    private val networkHelper: NetworkHelper,
    private val payment: Payment
) : ViewModel() {

    private val _cardIssues = MutableLiveData<Resource<List<CardIssue>>>()
    val cardIssues: LiveData<Resource<List<CardIssue>>>
        get() = _cardIssues

    fun fetchCardIssues() {
        viewModelScope.launch {
            _cardIssues.postValue(Resource.loading(null))
            if (networkHelper.isNetworkConnected()) {
                payment.paymentMethod?.id?.let { id ->
                    bankRepository.getCardIssuers(id).let {
                        if (it.isSuccessful) {
                            _cardIssues.postValue(Resource.success(bankRepository.mapCardIssues(it)))
                        } else {
                            _cardIssues.postValue(Resource.error(it.errorBody().toString(), null))
                        }
                    }
                }
            } else {
                _cardIssues.postValue(Resource.error("No internet connection", null))
            }
        }
    }

    fun onCardIssueSelected(cardIssue: CardIssue) {
        payment.cardIssue = cardIssue
    }
}