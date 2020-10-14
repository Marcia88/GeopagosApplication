package com.example.android.geopagosapplication.di.module.installment

import com.example.android.geopagosapplication.ui.installment.viewmodel.InstallmentViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val installmentViewModelModule = module {
    viewModel {
        InstallmentViewModel(get(),get(), get())
    }
}