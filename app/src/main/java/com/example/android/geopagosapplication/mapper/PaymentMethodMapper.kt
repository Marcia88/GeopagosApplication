package com.example.android.geopagosapplication.mapper

import com.example.android.geopagosapplication.dto.paymentmethod.PaymentMethodDTO
import com.example.android.geopagosapplication.dto.paymentmethod.PaymentType
import com.example.android.geopagosapplication.model.PaymentMethod

class PaymentMethodMapper : ListMapper<PaymentMethodDTO, PaymentMethod> {

    private fun map(input: PaymentMethodDTO): PaymentMethod {
        return PaymentMethod(
            input.id,
            input.name,
            paymentType(input.paymentTypeId),
            input.thumbnail
        )
    }

    private fun paymentType(paymentTypeId: PaymentType?): String? {
        return if (paymentTypeId == PaymentType.CREDIT_CARD) CREDIT else DEBIT
    }

    override fun map(input: List<PaymentMethodDTO>): List<PaymentMethod> {
        val paymentMethods = mutableListOf<PaymentMethod>()
        input.forEach {
            paymentMethods.add(map(it))
        }
        return paymentMethods
    }

    companion object {
        const val CREDIT = "Credit"
        const val DEBIT = "Debit"
    }
}