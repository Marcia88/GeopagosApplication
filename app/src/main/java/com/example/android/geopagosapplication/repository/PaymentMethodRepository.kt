package com.example.android.geopagosapplication.repository

import com.example.android.geopagosapplication.api.ApiHelper
import com.example.android.geopagosapplication.dto.paymentmethod.PaymentMethodDTO
import com.example.android.geopagosapplication.mapper.PaymentMethodMapper
import com.example.android.geopagosapplication.model.PaymentMethod
import retrofit2.Response

class PaymentMethodRepository(private val apiHelper: ApiHelper) {
    private val paymentMethodMapper = PaymentMethodMapper()

    suspend fun getPaymentMethods() = apiHelper.getPaymentMethods()

    fun mapPaymentMethods(paymentMethodsDTO: Response<List<PaymentMethodDTO>>): List<PaymentMethod> {
        return paymentMethodMapper.map(paymentMethodsDTO.body().orEmpty())
    }
}