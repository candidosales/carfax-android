package com.carfax.application.base.common.utils

/**
 * Created by candidosg on 09/03/18.
 */
class StringUtils {

    /**
     * Use to replace the placeholders for strings that use the format "text {{placeholder}} text".
     * @param string        string to apply substitutions on
     * @param substitutions key value pairs (placeholder, substitutionString)
     * @return string with substitutions applied
     */
    fun applySubstitutionsToString(
        string: String,
        vararg substitutions: Pair<String, String>
    ): String {
        var primaryString = string

        for ((first, second) in substitutions) {
            primaryString = primaryString.replace("{{$first}}", second)
        }
        return primaryString
    }

    fun removePunctuation(string: String): String {
        return string.replace("[^0-9 ]".toRegex(), "")
    }

    fun hasRepeatedCharacters(string: String): Boolean {
        val letter = string.split("".toRegex()).dropLastWhile { it.isEmpty() }
            .toTypedArray() // here you get each letter in to a string array

        for (i in 0 until letter.size - 2) {
            if (letter[i] == letter[i + 1] && letter[i + 1] == letter[i + 2]) {
                return true //return true as it has 3 sequential same character
            }
        }

        return false //If you reach here that means there are no consecutive numbers therefore return false.
    }


    fun hasSequentialNumbers(string: String): Boolean {
        val letter = string.split("".toRegex()).dropLastWhile { it.isEmpty() }
            .toTypedArray() // here you get each letter in to a string array

        for (i in 1 until letter.size - 2) {
            if (letter[i + 1].toInt() == letter[i].toInt() + 1 && letter[i + 2].toInt() == letter[i + 1].toInt() + 1) {
                return true //return true as it has consecutive numbers in ascending order
            }
        }

        for (i in 1 until letter.size - 2) {
            if (letter[i].toInt() == (letter[i + 1].toInt() + 1) && (letter[i + 1].toInt() == letter[i + 2].toInt() + 1)) {
                return true //return true as it has consecutive numbers in descending order
            }
        }
        return false
    }
}