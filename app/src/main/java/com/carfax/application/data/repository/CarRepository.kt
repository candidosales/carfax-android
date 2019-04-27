package com.carfax.application.data.repository

import com.carfax.application.cars.model.Car
import io.reactivex.Completable
import io.reactivex.Flowable

interface CarRepository {

    open fun clear(): Completable

    open fun save(cars: List<Car>): Completable

    open fun getCars(): Flowable<List<Car>>

}