package com.carfax.application.base.extensions

import io.reactivex.Flowable
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

/**
 * Extension function to add a Disposable to a CompositeDisposable
 */
fun Disposable.addToComposite(compositeDisposable: CompositeDisposable) {
    compositeDisposable.add(this)
}

/**
 * Extension function to subscribe on the background thread for a Observable
 * */
fun <T> Single<T>.performOnBack(): Single<T> {
    return this.subscribeOn(Schedulers.io())
}

/**
 * Extension function to observe on computation thread for a Single
 * */
fun <T> Single<T>.observeOnComputation(): Single<T> {
    return this.observeOn(Schedulers.computation())
}


/**
 * Extension function to observe on computation thread for a Single
 * */
fun <T> Flowable<T>.observeOnComputation(): Flowable<T> {
    return this.observeOn(Schedulers.computation())
}
