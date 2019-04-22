package com.carfax.application.cars.data

import com.carfax.application.base.data.BaseRemoteRepository
import com.carfax.application.cars.model.Car
import io.reactivex.Single


class CarsRepository constructor(
    private val carsMapper: CarsMapper,
    private val carService: CarService,
    private val baseRemoteRepository: BaseRemoteRepository
) {

    fun getCars(): Single<List<Car>> {
        return baseRemoteRepository.checkConnectionAndThenMapper(carsMapper) {
            carService.getCars()
        }
    }
}