package com.carfax.application.cars.presentation

import com.carfax.application.R
import com.carfax.application.base.presentation.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class CarFragment : BaseFragment() {
    override val layoutId: Int
        get() = R.layout.fragment_car

    private val carsViewModel: CarsViewModel by viewModel()
}