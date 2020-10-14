package com.example.android.geopagosapplication.model

data class Payment(
    var amount: Double = 0.0,
    var paymentMethod: PaymentMethod? = null,
    var cardIssue: CardIssue? = null
)