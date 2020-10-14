package com.example.android.geopagosapplication.di.module.paymentmethod

import com.example.android.geopagosapplication.ui.paymentmethod.viewmodel.PaymentMethodViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val paymentMethodViewModelModule = module {
    viewModel {
        PaymentMethodViewModel(get(),get(), get())
    }
}