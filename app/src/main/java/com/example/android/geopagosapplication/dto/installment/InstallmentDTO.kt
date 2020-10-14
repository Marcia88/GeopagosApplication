package com.example.android.geopagosapplication.dto.installment

import com.squareup.moshi.Json

data class InstallmentDTO(
    @Json(name = "payer_costs")
    val payerCosts: PayerCostsDTO? = null
)