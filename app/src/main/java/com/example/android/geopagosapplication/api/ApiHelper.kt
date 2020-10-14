package com.example.android.geopagosapplication.api

import com.example.android.geopagosapplication.dto.cardissues.CardIssueDTO
import com.example.android.geopagosapplication.dto.installment.InstallmentDTO
import com.example.android.geopagosapplication.dto.paymentmethod.PaymentMethodDTO
import retrofit2.Response

interface ApiHelper {

    suspend fun getPaymentMethods(): Response<List<PaymentMethodDTO>>

    suspend fun getCardIssues(paymentMethodId: String): Response<List<CardIssueDTO>>

    suspend fun getInstallments(
        paymentMethodId: String,
        amount: Double,
        cardIssueId: String
    ): Response<List<InstallmentDTO>>
}