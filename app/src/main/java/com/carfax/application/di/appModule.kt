package com.carfax.application.di

import com.carfax.application.cars.data.CarsMapper
import com.carfax.application.cars.data.CarsRepository
import com.carfax.application.cars.presentation.CarAdapter
import com.carfax.application.cars.presentation.CarViewMapper
import com.carfax.application.cars.presentation.CarsFragment
import com.carfax.application.cars.presentation.CarsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val appModule = module {
    factory { CarsFragment() }
    factory { CarsMapper() }
    factory { CarViewMapper(get(), get()) }
    factory { CarAdapter() }
    factory { CarsRepository(get(), get(), get()) }
    viewModel { CarsViewModel(get(), get()) }
}