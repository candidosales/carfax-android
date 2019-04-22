package com.carfax.application.cars.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.carfax.application.base.extensions.addToComposite
import com.carfax.application.base.extensions.observeOnComputation
import com.carfax.application.base.presentation.BaseViewModel
import com.carfax.application.base.presentation.ViewActionState
import com.carfax.application.cars.data.CarsRepository
import com.carfax.application.cars.model.Car

class CarsViewModel constructor(
    private val carsRepository: CarsRepository
) : BaseViewModel() {

    private val _cars = MutableLiveData<ViewActionState<List<Car>>>()
    val cars: LiveData<ViewActionState<List<Car>>>
        get() = _cars


    fun fetchCars() {
        _cars.postValue(ViewActionState.loading())
        carsRepository
            .getCars()
            .observeOnComputation()
            .subscribe({ result ->
                _cars.postValue(ViewActionState.complete(result))
            }, { ex ->
                handleFailure(ex, _cars)
            })
            .addToComposite(compositeDisposable)
    }
}