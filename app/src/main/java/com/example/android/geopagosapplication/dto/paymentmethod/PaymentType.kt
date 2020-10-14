package com.example.android.geopagosapplication.dto.paymentmethod

import com.squareup.moshi.Json

enum class PaymentType {
    @Json(name = "credit_card") CREDIT_CARD,
    @Json(name = "debit_card") DEBIT_CARD
}