package com.carfax.application.base.presentation

import com.carfax.application.base.exception.BusinessException

sealed class ViewError {
    object NoInternetConnection : ViewError()
    object NoMappedError : ViewError()
    object NoMappedHTTPCode : ViewError()
    object UnauthorizedUser : ViewError()
    data class BusinessValidation(val type: BusinessException) : ViewError()
}