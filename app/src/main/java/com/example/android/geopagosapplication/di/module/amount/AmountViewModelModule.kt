package com.example.android.geopagosapplication.di.module.amount

import com.example.android.geopagosapplication.ui.amount.viewmodel.AmountViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val amountViewModelModule = module {
    viewModel {
        AmountViewModel(get())
    }
}