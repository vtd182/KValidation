package org.example.rules

import org.example.core.ValidationError
import org.example.core.ValidationRule

class NotEmptyRule : ValidationRule {
    override fun validate(fieldName: String, value: Any?): ValidationError? {
        return if (value == null || (value is String && value.isBlank())) {
            ValidationError(fieldName, "$fieldName must not be empty")
        } else null
    }
}