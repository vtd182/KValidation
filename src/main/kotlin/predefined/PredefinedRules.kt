package org.example.predefined

import org.example.core.ValidationRule


object PredefinedRules {
    fun email(): ValidationRule {
        return object : ValidationRule {
            override fun validate(value: Any?): String? {
                val emailRegex = "[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}".toRegex()
                return if (value == null || !emailRegex.matches(value.toString())) {
                    "Invalid email format"
                } else null
            }
        }
    }

    fun notEmpty(): ValidationRule {
        return object : ValidationRule {
            override fun validate(value: Any?): String? {
                return if (value == null || value.toString().isEmpty()) {
                    "Field cannot be empty"
                } else null
            }
        }
    }
}
