package com.example.android.geopagosapplication.dto.paymentmethod

import com.squareup.moshi.Json

enum class Validation {
    @Json(name = "standard") STANDARD,
    @Json(name = "none") NONE
}