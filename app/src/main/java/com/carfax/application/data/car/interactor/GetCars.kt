package com.carfax.application.data.car.interactor

import com.carfax.application.cars.model.Car
import com.carfax.application.data.DataRepository
import com.carfax.application.data.executor.PostExecutionThread
import com.carfax.application.data.executor.ThreadExecutor
import com.carfax.application.data.interactor.FlowableUseCase
import io.reactivex.Flowable

open class GetCars(val repository: DataRepository,
                   threadExecutor: ThreadExecutor,
                   postExecutionThread: PostExecutionThread
):
    FlowableUseCase<List<Car>, Void?>(threadExecutor, postExecutionThread) {

    public override fun buildUseCaseObservable(params: Void?): Flowable<List<Car>> {
        return repository.get()
    }
}