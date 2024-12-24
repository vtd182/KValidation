package org.example.rules

import org.example.core.ValidationError
import org.example.core.ValidationRule

class CustomRule(private val condition: (Any?) -> Boolean, private val errorMessage: String) : ValidationRule {
    override fun validate(fieldName: String, value: Any?): ValidationError? {
        return if (!condition(value)) {
            ValidationError(fieldName, errorMessage)
        } else null
    }
}