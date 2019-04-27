package com.carfax.application.data

import com.carfax.application.cars.model.Car
import com.carfax.application.data.repository.CarRepository
import com.carfax.application.data.source.CarDataStoreFactory
import io.reactivex.Completable
import io.reactivex.Flowable

/**
 * Provides an implementation of the [BufferooRepository] interface for communicating to and from
 * data sources
 */
open class CarDataRepository(private val factory: CarDataStoreFactory): CarRepository {

    override fun clear(): Completable {
        return factory.retrieveCacheDataStore().clear()
    }

    override fun save(cars: List<Car>): Completable {
        return factory.retrieveCacheDataStore().save(cars)
    }

    override fun getCars(): Flowable<List<Car>> {
        return factory.retrieveCacheDataStore().isCached()
            .flatMapPublisher {
                factory.retrieveDataStore(it).getCars()
            }
            .flatMap {
                save(it).toSingle { it }.toFlowable()
            }
    }

}