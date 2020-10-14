package com.example.android.geopagosapplication.dto.paymentmethod

import com.squareup.moshi.Json

data class PaymentMethodDTO(

    @Json(name = "id")
    val id: String? = null,
    @Json(name = "name")
    val name: String? = null,
    @Json(name = "payment_type_id")
    val paymentTypeId: PaymentType? = null,
    @Json(name = "secure_thumbnail")
    val thumbnail: String? = null
)