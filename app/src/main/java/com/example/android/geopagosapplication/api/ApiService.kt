package com.example.android.geopagosapplication.api

import com.example.android.geopagosapplication.BuildConfig
import com.example.android.geopagosapplication.dto.cardissues.CardIssueDTO
import com.example.android.geopagosapplication.dto.installment.InstallmentDTO
import com.example.android.geopagosapplication.dto.paymentmethod.PaymentMethodDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET(GET_PAYMENT_METHOD)
    suspend fun getPaymentMethods(
        @Query(API_KEY) apiKey: String = BuildConfig.API_KEY
    ): Response<List<PaymentMethodDTO>>

    @GET(GET_CARD_ISSUES)
    suspend fun getCardIssuers(
        @Query(API_KEY) apiKey: String = BuildConfig.API_KEY,
        @Query(PAYMENT_METHOD_ID) paymentMethodId: String
    ): Response<List<CardIssueDTO>>

    @GET(GET_INSTALLMENTS)
    suspend fun getInstallments(
        @Query(API_KEY) apiKey: String = BuildConfig.API_KEY,
        @Query(PAYMENT_METHOD_ID) paymentMethodId: String,
        @Query(AMOUNT) amount: Double,
        @Query(ISSUER_ID) issuerId: String
    ): Response<List<InstallmentDTO>>

    companion object {
        private const val API_KEY = "public_key"
        private const val PAYMENT_METHOD_ID = "payment_method_id"
        private const val AMOUNT = "amount"
        private const val ISSUER_ID = "issuer.id"

        private const val GET_CARD_ISSUES = "payment_methods/card_issuers"
        private const val GET_PAYMENT_METHOD = "payment_methods"
        private const val GET_INSTALLMENTS = "payment_methods/installments"
    }
}