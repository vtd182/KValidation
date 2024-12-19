package org.example.utils

object ValidationUtils {
    fun isValidEmail(email: String): Boolean {
        val regex = "[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}".toRegex()
        return regex.matches(email)
    }
}
