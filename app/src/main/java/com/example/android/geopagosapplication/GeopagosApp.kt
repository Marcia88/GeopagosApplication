package com.example.android.geopagosapplication

import android.app.Application
import com.example.android.geopagosapplication.di.module.amount.amountViewModelModule
import com.example.android.geopagosapplication.di.module.appModule
import com.example.android.geopagosapplication.di.module.bank.bankModule
import com.example.android.geopagosapplication.di.module.bank.bankViewModelModule
import com.example.android.geopagosapplication.di.module.installment.installmentModule
import com.example.android.geopagosapplication.di.module.installment.installmentViewModelModule
import com.example.android.geopagosapplication.di.module.paymentmethod.paymentMethodModule
import com.example.android.geopagosapplication.di.module.paymentmethod.paymentMethodViewModelModule
import com.facebook.drawee.backends.pipeline.Fresco
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class GeopagosApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@GeopagosApp)
            modules(
                listOf(
                    appModule, paymentMethodModule, paymentMethodViewModelModule,
                    amountViewModelModule, bankModule, bankViewModelModule, installmentModule,
                    installmentViewModelModule
                )
            )
        }

        Fresco.initialize(this)
    }
}