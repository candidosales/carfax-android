package com.carfax.application.cars.presentation

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.carfax.application.R
import com.carfax.application.base.presentation.BaseFragment
import com.carfax.application.base.presentation.ViewActionState
import com.carfax.application.cars.model.Car
import kotlinx.android.synthetic.main.fragment_cars.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber


class CarsFragment : BaseFragment() {

    override val layoutId: Int
        get() = R.layout.fragment_cars

    private val carsViewModel: CarsViewModel by viewModel()
    val carAdapter: CarAdapter by inject()


    private lateinit var cars: List<CarView>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupCarRecycler()
        getHeroes()
    }

    private fun setupCarRecycler() {
        carsRecyclerView.layoutManager = LinearLayoutManager(context)
        carsRecyclerView.adapter = carAdapter
    }

    private fun getHeroes() {
//        toggleLoading(true)

        carsViewModel.cars.observe(this, Observer {
            if (it is ViewActionState.Complete) {
                cars = it.result
                updateListView(cars)
            }

            if (it is ViewActionState.Error) {
                Timber.e("Error - ${it.error}")
            }
        })

        carsViewModel.fetchCars()
    }

//    private fun handleDataState(viewActionState: ViewActionState<T>) {
//        when (browseState) {
//            is BrowseState.Loading -> setupScreenForLoadingState()
//            is BrowseState.Success -> setupScreenForSuccess(browseState.data)
//            is BrowseState.Error -> setupScreenForError(browseState.errorMessage)
//        }
//    }

    private fun toggleLoading(toggle: Boolean) {
        loading.visibility = if (toggle) View.VISIBLE else View.GONE
//        nestedScroll.visibility = if (toggle) View.GONE else View.VISIBLE
    }

//    private fun showHeroes(state: ViewActionState<List<Car>>?) {
//        if (state is ViewActionState.Complete) {
//            cars = state.result
//            updateAdapter()
//        }
//        progress(state is ViewActionState.Loading)
//    }

//    private fun progress(loading: Boolean) {
//        carsRecyclerView.hideOrShow(show = !loading)
//    }

//    private fun updateAdapter() {
//        if (!::cars.isInitialized) {
//            return
//        }
//
//        val layoutManager = LinearLayoutManager(
//            context,
//            LinearLayout.HORIZONTAL,
//            false
//        )
//
//        val adapterCars = CarAdapter(cars, currencyUtils)
//        carsRecyclerView.adapter = adapterCars
//        carsRecyclerView.layoutManager = layoutManager
//    }

    private fun updateListView(cars: List<CarView>) {
        carAdapter.cars = cars
        carAdapter.notifyDataSetChanged()
    }
}