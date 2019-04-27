package com.carfax.application.di

import com.carfax.application.UiThread

import androidx.room.Room
import com.carfax.application.cache.CarCacheImpl
import com.carfax.application.cache.PreferencesHelper
import com.carfax.application.cache.db.CarfaxDatabase
import com.carfax.application.cache.mapper.CarEntityMapper
import com.carfax.application.cars.data.CarsMapper
import com.carfax.application.cars.data.CarsRepository
import com.carfax.application.cars.presentation.CarAdapter
import com.carfax.application.cars.presentation.CarViewMapper
import com.carfax.application.cars.presentation.CarsFragment
import com.carfax.application.cars.presentation.CarsViewModel
import com.carfax.application.data.CarDataRepository
import com.carfax.application.data.car.interactor.GetCars
import com.carfax.application.data.executor.JobExecutor
import com.carfax.application.data.executor.PostExecutionThread
import com.carfax.application.data.executor.ThreadExecutor
import com.carfax.application.data.repository.CarRepository
import com.carfax.application.data.source.CarDataStore
import com.carfax.application.data.source.CarDataStoreFactory
import com.carfax.application.remote.CarRemoteImpl
import com.carfax.application.remote.mapper.CarsEntityMapper
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module


val appModule = module(override=true) {

    single { PreferencesHelper(androidContext()) }

    factory { CarsEntityMapper() }

    single { JobExecutor() as ThreadExecutor }
    single { UiThread() as PostExecutionThread }

    single { Room.databaseBuilder(androidContext(),
        CarfaxDatabase::class.java, "carfax.db")
        .build() }
    factory { get<CarfaxDatabase>().cachedCarDao() }

    factory<CarDataStore>(named("remote")) { CarRemoteImpl(get(), get()) }
    factory<CarDataStore>(named("local")) { CarCacheImpl(get(), get(), get()) }
    factory { CarDataStoreFactory(get(named("local")), get(named("remote"))) }

    factory { CarEntityMapper() }
//    factory { BufferooServiceFactory.makeBuffeoorService(BuildConfig.DEBUG) }

    factory<CarRepository> { CarDataRepository(get()) }
}


val carsModule = module(override = true) {
    factory { CarsFragment() }
    factory { GetCars(get(), get(), get()) }
//    viewModel { BrowseBufferoosViewModel(get()) }


    factory { CarsFragment() }
    factory { CarsMapper() }
    factory { CarViewMapper(get(), get()) }
    factory { CarAdapter() }
    factory { CarsRepository(get(), get(), get()) }
    viewModel { CarsViewModel(get(), get()) }
}

//val appModule = module {
//
//
//
//
//    factory { CarsFragment() }
//    factory { CarsMapper() }
//    factory { CarViewMapper(get(), get()) }
//    factory { CarAdapter() }
//    factory { CarsRepository(get(), get(), get()) }
//    viewModel { CarsViewModel(get(), get()) }
//}