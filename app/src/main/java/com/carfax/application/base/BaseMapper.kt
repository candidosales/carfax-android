package com.carfax.application.base

import com.carfax.application.base.exception.EssentialParamMissingException
import io.reactivex.functions.Function

/**
 * Base mapper to all Mappers extend
 *
 * @param Raw The result from server
 * @param Model The object to create from Raw
 */
abstract class BaseMapper<Raw, Model> : Function<Raw, Model> {

    @Throws(EssentialParamMissingException::class)
    override fun apply(raw: Raw): Model {
        assertEssentialParams(raw)
        return copyValues(raw)
    }

    /**
     * Check if the required parameters were returned from server
     *
     * @param raw The result from server
     * @throws EssentialParamMissingException When a required parameter is missing
     */
    private fun assertEssentialParams(raw: Raw) {
        val fields = mutableListOf<String>()
        checkParams(raw, fields)
        if (fields.isNotEmpty()) {
            val params = fields.joinToString(prefix = "[", postfix = "]")
            throw EssentialParamMissingException(missingParam = params, rawObject = raw!!)
        }
    }

    /**
     * Check if the specific implementation parameters were return from server
     *
     * @param raw The result from server
     * @param missingFields The list used to add the missing parameters
     */
    protected abstract fun checkParams(raw: Raw, missingFields: MutableList<String>)

    /**
     * Create a [Model] using the values in [Raw]
     *
     * @param raw The result from server
     * @return A model with the raw's values
     */
    protected abstract fun copyValues(raw: Raw): Model
}