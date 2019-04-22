package com.carfax.application.base.data

import com.carfax.application.base.exception.NetworkError
import com.carfax.application.base.extensions.performOnBack
import com.carfax.application.base.network.NetworkHandler
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.functions.Function

class BaseRemoteRepository constructor(
    private val networkHandler: NetworkHandler
) {
    /**
     * Function to check if there is internet connection
     *
     * @param body Another function to execute when there is internet connection
     * @return An [Completable] with [NetworkError] when there is no internet
     * connection or with the body function result
     */
    fun <T, R> checkConnectionAndThenMapper(
        mapper: Function<T, R>,
        body: () -> Single<T>
    ): Single<R> {
        return when (networkHandler.isConnected) {
            false -> Single.error(NetworkError)
            true -> body()
                .performOnBack()
                .map(mapper)
        }
    }
}