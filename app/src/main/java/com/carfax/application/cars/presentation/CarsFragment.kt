package com.carfax.application.cars.presentation

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.carfax.application.R
import com.carfax.application.base.common.utils.CurrencyUtils
import com.carfax.application.base.extensions.hideOrShow
import com.carfax.application.base.presentation.BaseFragment
import com.carfax.application.base.presentation.ViewActionState
import com.carfax.application.cars.model.Car
import kotlinx.android.synthetic.main.fragment_cars.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class CarsFragment : BaseFragment() {

    override val layoutId: Int
        get() = R.layout.fragment_cars

    private val viewModel: CarsViewModel by viewModel()
    private val currencyUtils: CurrencyUtils by inject()

    private lateinit var cars: List<Car>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getHeroes()
    }

    private fun getHeroes() {
        toggleLoading(true)

        viewModel.cars.observe(this, Observer {
            toggleLoading(false)
            if (it is ViewActionState.Complete) {
                cars = it.result
                updateAdapter()
            }
            toggleLoading(it is ViewActionState.Loading)
        })
    }

    private fun toggleLoading(toggle: Boolean) {
        loading.visibility = if (toggle) View.VISIBLE else View.GONE
        nestedScroll.visibility = if (toggle) View.GONE else View.VISIBLE
    }

//    private fun showHeroes(state: ViewActionState<List<Car>>?) {
//        if (state is ViewActionState.Complete) {
//            cars = state.result
//            updateAdapter()
//        }
//        progress(state is ViewActionState.Loading)
//    }
//
//    private fun progress(loading: Boolean) {
//        carsRecyclerView.hideOrShow(show = !loading)
//    }

    private fun updateAdapter() {
        if (!::cars.isInitialized) {
            return
        }

        val layoutManager = LinearLayoutManager(context)
        val adapterCars = CarAdapter(currencyUtils)
        carsRecyclerView.adapter = adapterCars
        carsRecyclerView.layoutManager = layoutManager

        adapterCars.setDataSource(cars)
    }
}