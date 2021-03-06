package com.carfax.application.base.extensions

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.*


/**
 * Extension function to get a [ViewModel] from [ViewModelProviders]
 * using this factory and a body to execute actions with this ViewModel
 *
 * @param factory The factory used to create the ViewModel
 * @param body The actions to execute with this ViewModel
 * @return The instance created before or a new instance
 */
inline fun <reified T : ViewModel> Fragment.viewModel(
    factory: ViewModelProvider.Factory,
    body: T.() -> Unit
): T {
    val vm = ViewModelProviders.of(this, factory)[T::class.java]
    vm.body()
    return vm
}

/**
 * Extension function to get a [ViewModel] from [ViewModelProviders]
 * using this factory and a body to execute actions with this ViewModel
 *
 * @param factory The factory used to create the ViewModel
 * @return The instance created before or a new instance
 */
inline fun <reified T : ViewModel> FragmentActivity.viewModel(
    factory: ViewModelProvider.Factory
): T = ViewModelProviders.of(this, factory)[T::class.java]

/**
 * Extension function to get a [ViewModel] from [ViewModelProviders]
 * using this factory and a body to execute actions with this ViewModel
 *
 * @param factory The factory used to create the ViewModel
 * @param body The actions to execute with this ViewModel
 * @return The instance created before or a new instance
 */
inline fun <reified T : ViewModel> FragmentActivity.viewModel(
    factory: ViewModelProvider.Factory,
    body: T.() -> Unit
): T {
    val vm = viewModel<T>(factory)
    vm.body()
    return vm
}

/**
 * Extension function to attach a behavior to any [LiveData]
 *
 * @param liveData The current [LiveData]
 * @param body The action to execute
 */
fun <T : Any, L : LiveData<T>> LifecycleOwner.observe(liveData: L, body: (T?) -> Unit) =
    liveData.observe(this, Observer(body))