package com.example.android.geopagosapplication.di.module.bank

import com.example.android.geopagosapplication.repository.BankRepository
import org.koin.dsl.module

val bankModule = module {
    single {
        BankRepository(get())
    }
}