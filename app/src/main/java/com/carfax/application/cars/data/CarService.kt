package com.carfax.application.cars.data

import io.reactivex.Single
import retrofit2.http.GET

interface CarService {

    @GET("5bd92b00adf9f5652a68b980")
    fun getCars(): Single<CarsResponseRaw>

}