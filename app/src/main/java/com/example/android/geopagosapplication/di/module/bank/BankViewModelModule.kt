package com.example.android.geopagosapplication.di.module.bank

import com.example.android.geopagosapplication.ui.bank.viewmodel.BankViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val bankViewModelModule = module {
    viewModel {
        BankViewModel(get(),get(), get())
    }
}