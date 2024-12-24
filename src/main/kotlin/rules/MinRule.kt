package org.example.rules

import org.example.core.ValidationError
import org.example.core.ValidationRule

class MinRule(private val min: Int) : ValidationRule {
    override fun validate(fieldName: String, value: Any?): ValidationError? {
        return if (value is Int && value < min) {
            ValidationError(fieldName, "$fieldName must be at least $min")
        } else null
    }
}