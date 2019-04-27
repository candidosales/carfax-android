package com.carfax.application.data.source

import com.carfax.application.cars.model.Car
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single

/**
 * Interface defining methods for the data operations related to Bufferroos.
 * This is to be implemented by external data source layers, setting the requirements for the
 * operations that need to be implemented
 */
interface CarDataStore {

    fun clear(): Completable

    fun save(cars: List<Car>): Completable

    fun getCars(): Flowable<List<Car>>

    fun isCached(): Single<Boolean>

    fun setLastCacheTime(lastCache: Long)

    fun isExpired(): Boolean

}