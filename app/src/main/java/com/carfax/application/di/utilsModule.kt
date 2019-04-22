package com.carfax.application.di

import com.carfax.application.base.common.utils.CurrencyUtils
import org.koin.dsl.module


val utilsModule = module {
    single { CurrencyUtils() }
}