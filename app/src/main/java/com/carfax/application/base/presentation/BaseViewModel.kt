package com.carfax.application.base.presentation

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.carfax.application.base.exception.*
import io.reactivex.disposables.CompositeDisposable

/**
 * Base ViewModel class with default BaseException handling.
 */
abstract class BaseViewModel : ViewModel() {

    protected val compositeDisposable = CompositeDisposable()

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }

    /**
     * Remove all observers attached to a LiveData
     * This is required for a previous view not stay listening for changes without destroy the
     * ViewModel
     *
     * @param owner The LifecycleOwner where the observers were attached
     */
    open fun removeObservers(owner: LifecycleOwner) {}

    /**
     * Transform business or backend exceptions in results that view can understand
     *
     * @param ex A generic exception received during the process
     * @param liveData A [MutableLiveData] used to send a [ViewError]
     */
    protected fun <T> handleFailure(
        ex: Throwable,
        liveData: MutableLiveData<ViewActionState<T>>
    ) {
        val error = when (ex) {
            NetworkError, is UnknownBaseHostError -> ViewError.NoInternetConnection
            UnauthorizedError -> ViewError.UnauthorizedUser
            is HttpError -> ViewError.NoMappedHTTPCode
            is BusinessException -> ViewError.BusinessValidation(ex)
            else -> ViewError.NoMappedError
        }
        liveData.postValue(ViewActionState.failure(error))
    }
}
