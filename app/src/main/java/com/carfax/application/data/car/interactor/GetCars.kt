package com.carfax.application.data.car.interactor

import com.carfax.application.cars.model.Car
import com.carfax.application.data.CarDataRepository
import com.carfax.application.data.executor.PostExecutionThread
import com.carfax.application.data.executor.ThreadExecutor
import com.carfax.application.data.interactor.FlowableUseCase
import com.carfax.application.data.repository.CarRepository
import io.reactivex.Flowable

open class GetCars(
    private val repository: CarRepository,
    private val threadExecutor: ThreadExecutor,
    private val postExecutionThread: PostExecutionThread
):
    FlowableUseCase<List<Car>, Void?>(threadExecutor, postExecutionThread) {

    public override fun buildUseCaseObservable(params: Void?): Flowable<List<Car>> {
        return repository.getCars()
    }
}