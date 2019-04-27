package com.carfax.application.cache

import com.carfax.application.cache.db.CarfaxDatabase
import com.carfax.application.cache.mapper.CarEntityMapper
import com.carfax.application.cars.model.Car
import com.carfax.application.data.source.CarDataStore
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single

class CarCacheImpl(
    private val carfaxDatabase: CarfaxDatabase,
    private val entityMapper: CarEntityMapper,
    private val preferencesHelper: PreferencesHelper
): CarDataStore {

    private val EXPIRATION_TIME = (60 * 10 * 1000).toLong()

    /**
     * Retrieve an instance from the database, used for tests.
     */
    internal fun getDatabase(): CarfaxDatabase {
        return carfaxDatabase
    }

    /**
     * Remove all the data from all the tables in the database.
     */
    override fun clear(): Completable {
        return Completable.defer {
            carfaxDatabase.cachedCarDao().deleteAll()
            Completable.complete()
        }
    }

    /**
     * Save the given list of [Car] instances to the database.
     */
    override fun save(cars: List<Car>): Completable {
        return Completable.defer {
            cars.forEach {
                carfaxDatabase.cachedCarDao().insert(entityMapper.mapToCached(it))
            }
            Completable.complete()
        }
    }

    /**
     * Retrieve a list of [Car] instances from the database.
     */
    override fun getCars(): Flowable<List<Car>> {
        return Flowable.defer {
            Flowable.just(carfaxDatabase.cachedCarDao().getAll())
        }.map {
            it.map { entityMapper.mapFromCached(it) }
        }
    }

    /**
     * Check whether there are instances of [CachedCar] stored in the cache.
     */
    override fun isCached(): Single<Boolean> {
        return Single.defer {
            Single.just(carfaxDatabase.cachedCarDao().getAll().isNotEmpty())
        }
    }

    /**
     * Set a point in time at when the cache was last updated.
     */
    override fun setLastCacheTime(lastCache: Long) {
        preferencesHelper.lastCacheTime = lastCache
    }

    /**
     * Check whether the current cached data exceeds the defined [EXPIRATION_TIME] time.
     */
    override fun isExpired(): Boolean {
        val currentTime = System.currentTimeMillis()
        val lastUpdateTime = this.getLastCacheUpdateTimeMillis()
        return currentTime - lastUpdateTime > EXPIRATION_TIME
    }

    /**
     * Get in millis, the last time the cache was accessed.
     */
    private fun getLastCacheUpdateTimeMillis(): Long {
        return preferencesHelper.lastCacheTime
    }

}