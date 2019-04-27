package com.carfax.application.di

import com.carfax.application.base.common.utils.CurrencyUtils
import com.carfax.application.base.common.utils.StringUtils
import com.carfax.application.base.presentation.providers.StringProvider
import org.koin.dsl.module


val utilsModule = module {
    single { CurrencyUtils() }
    single { StringUtils() }
    single { StringProvider(get(), get()) }
}