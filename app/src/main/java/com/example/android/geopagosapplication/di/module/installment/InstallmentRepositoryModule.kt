package com.example.android.geopagosapplication.di.module.installment

import com.example.android.geopagosapplication.repository.InstallmentRepository
import org.koin.dsl.module

val installmentModule = module {
    single {
        InstallmentRepository(get())
    }
}