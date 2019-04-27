package com.carfax.application.base.presentation.providers

import android.content.Context
import androidx.annotation.StringRes
import com.carfax.application.base.common.utils.StringUtils

class StringProvider constructor(
    private val context: Context,
    private val stringUtils: StringUtils
) {

    /**
     * Use to replace the placeholders for strings that use the format "text {{placeholder}} text".
     * @param stringResId   string resource id
     * @param substitutions substitutions
     * @return string
     */
    fun getStringAndApplySubstitutions(@StringRes stringResId: Int, vararg substitutions: Pair<String, String>): String {
        return stringUtils.applySubstitutionsToString(
            context.getString(stringResId),
            *substitutions
        )
    }

    fun getStringAndApplySubstitutions(@StringRes stringResId: String, vararg substitutions: Pair<String, String>): String {
        return stringUtils.applySubstitutionsToString(stringResId, *substitutions)
    }
}