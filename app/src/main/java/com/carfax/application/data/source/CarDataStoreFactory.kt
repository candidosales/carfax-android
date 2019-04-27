package com.carfax.application.data.source

/**
 * Create an instance of a DataStore
 */
open class CarDataStoreFactory(
    private val cacheDataStore: CarDataStore,
    private val remoteDataStore: CarDataStore
) {

    /**
     * Returns a DataStore based on whether or not there is content in the cache and the cache
     * has not expired
     */
    open fun retrieveDataStore(isCached: Boolean): CarDataStore {
        if (isCached && !cacheDataStore.isExpired()) {
            return retrieveCacheDataStore()
        }
        return retrieveRemoteDataStore()
    }

    /**
     * Return an instance of the Cache Data Store
     */
    open fun retrieveCacheDataStore(): CarDataStore {
        return cacheDataStore
    }

    /**
     * Return an instance of the Remote Data Store
     */
    open fun retrieveRemoteDataStore(): CarDataStore {
        return remoteDataStore
    }

}