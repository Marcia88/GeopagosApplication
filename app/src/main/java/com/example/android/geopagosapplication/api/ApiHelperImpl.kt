package com.example.android.geopagosapplication.api

import com.example.android.geopagosapplication.dto.cardissues.CardIssueDTO
import com.example.android.geopagosapplication.dto.installment.InstallmentDTO
import com.example.android.geopagosapplication.dto.paymentmethod.PaymentMethodDTO
import retrofit2.Response

class ApiHelperImpl(private val apiService: ApiService) : ApiHelper {

    override suspend fun getPaymentMethods(): Response<List<PaymentMethodDTO>> =
        apiService.getPaymentMethods()

    override suspend fun getCardIssues(paymentMethodId: String): Response<List<CardIssueDTO>> =
        apiService.getCardIssuers(paymentMethodId = paymentMethodId)

    override suspend fun getInstallments(
        paymentMethodId: String,
        amount: Double,
        cardIssueId: String
    ): Response<List<InstallmentDTO>> =
        apiService.getInstallments(
            paymentMethodId = paymentMethodId,
            amount = amount,
            issuerId = cardIssueId
        )


}