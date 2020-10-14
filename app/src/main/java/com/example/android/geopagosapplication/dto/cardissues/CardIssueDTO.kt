package com.example.android.geopagosapplication.dto.cardissues

import com.squareup.moshi.Json

data class CardIssueDTO(

    @Json(name = "id")
    val id: String? = null,
    @Json(name = "name")
    val name: String? = null,
    @Json(name = "secure_thumbnail")
    val secure_thumbnail: String? = null
)