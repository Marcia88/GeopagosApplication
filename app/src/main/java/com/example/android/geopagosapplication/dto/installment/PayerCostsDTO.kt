package com.example.android.geopagosapplication.dto.installment

import com.squareup.moshi.Json

data class PayerCostsDTO(
    @Json(name = "recommended_message")
    val recommended_message: String? = null
)