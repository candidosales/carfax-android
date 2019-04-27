package com.carfax.application.data.source

/**
 * Create an instance of a DataStore
 */
open class DataStoreFactory(
    private val cacheDataStore: DataStore,
    private val remoteDataStore: DataStore
) {

    /**
     * Returns a DataStore based on whether or not there is content in the cache and the cache
     * has not expired
     */
    open fun retrieveDataStore(isCached: Boolean): DataStore {
        if (isCached && !cacheDataStore.isExpired()) {
            return retrieveCacheDataStore()
        }
        return retrieveRemoteDataStore()
    }

    /**
     * Return an instance of the Cache Data Store
     */
    open fun retrieveCacheDataStore(): DataStore {
        return cacheDataStore
    }

    /**
     * Return an instance of the Remote Data Store
     */
    open fun retrieveRemoteDataStore(): DataStore {
        return remoteDataStore
    }

}