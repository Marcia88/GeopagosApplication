package com.example.android.geopagosapplication.repository

import com.example.android.geopagosapplication.api.ApiHelper

class InstallmentRepository(private val apiHelper: ApiHelper) {

    suspend fun getInstallments(
        paymentMethodId: String,
        amount: Double,
        cardIssueId: String
    ) = apiHelper.getInstallments(paymentMethodId, amount, cardIssueId)

}