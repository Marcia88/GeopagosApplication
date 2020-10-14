package com.example.android.geopagosapplication.di.module.paymentmethod

import com.example.android.geopagosapplication.repository.PaymentMethodRepository
import org.koin.dsl.module

val paymentMethodModule = module {
    single {
        PaymentMethodRepository(get())
    }
}