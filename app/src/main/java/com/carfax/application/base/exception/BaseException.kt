package com.carfax.application.base.exception

/**
 * Base Class for handling errors/failures/exceptions.
 * Every feature specific should extend [BusinessException] class.
 */
sealed class BaseException(message: String = "") : RuntimeException(message)

/**
 * Object used to identify a generic http request exception
 */
object HttpError : BaseException("Server http response code is not 200, 400 or 403")

/**
 * Object used to identify a network without internet connection
 */
object NetworkError : BaseException()

/**
 * Object used to identify an user Unauthorized
 */
object UnauthorizedError : BaseException()

/**
 * Exception used to identify an invalid endpoint used in the app
 */
class UnknownBaseHostError(val url: String) : BaseException("Unknown host: $url")

/**
 * Extend this class for feature specific failures.
 *
 * */
abstract class BusinessException : BaseException()

/**
 * Exception thrown when an essential parameter is missing in the
 * backend/network response.
 *
 */
class EssentialParamMissingException(
    missingParam: String,
    rawObject: Any
) : BaseException("The params: $missingParam are missing in received object: $rawObject")