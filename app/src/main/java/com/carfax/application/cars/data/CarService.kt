package com.carfax.application.cars.data

import io.reactivex.Single
import retrofit2.http.GET

interface CarService {

    @GET("assignment.json")
    fun getCars(): Single<CarsResponseRaw>

}