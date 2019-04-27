package com.carfax.application.data

import com.carfax.application.cars.model.Car
import com.carfax.application.data.source.DataStoreFactory
import io.reactivex.Completable
import io.reactivex.Flowable

/**
 * Provides an implementation of the [BufferooRepository] interface for communicating to and from
 * data sources
 */
open class DataRepository(private val factory: DataStoreFactory) {

    fun clear(): Completable {
        return factory.retrieveCacheDataStore().clear()
    }

    fun save(cars: List<Car>): Completable {
        return factory.retrieveCacheDataStore().save(cars)
    }

    fun get(): Flowable<List<Car>> {
        return factory.retrieveCacheDataStore().isCached()
            .flatMapPublisher {
                factory.retrieveDataStore(it).get()
            }
            .flatMap {
                save(it).toSingle { it }.toFlowable()
            }
    }

}