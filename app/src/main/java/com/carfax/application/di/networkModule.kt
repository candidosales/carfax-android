package com.carfax.application.di

import com.carfax.application.BuildConfig
import com.carfax.application.base.data.BaseRemoteRepository
import com.carfax.application.base.network.NetworkHandler
import com.carfax.application.cars.data.CarService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import timber.log.Timber
import java.util.concurrent.TimeUnit


val networkModule = module {
    single { createOkHttpClient() }
    single { createWebService<CarService>(get()) }
    single { NetworkHandler(androidContext()) }
    single { BaseRemoteRepository(get()) }
}

fun createOkHttpClient(): OkHttpClient {
    val okHttpClientBuilder = OkHttpClient.Builder()
        .connectTimeout(120, TimeUnit.SECONDS)
        .readTimeout(120, TimeUnit.SECONDS)

    if (BuildConfig.DEBUG) {
        val interceptor = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger { message ->
            Timber.d(message)
        })
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        okHttpClientBuilder.addInterceptor(interceptor)
    }


    return okHttpClientBuilder.build()
}

inline fun <reified T> createWebService(okHttpClient: OkHttpClient): T {
    val retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.API_BASE_URL)
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(MoshiConverterFactory.create())
        .client(okHttpClient)
        .build()
    return retrofit.create(T::class.java)
}