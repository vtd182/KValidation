package org.example.rules

import org.example.core.ValidationError
import org.example.core.ValidationRule

class RegexRule(private val pattern: Regex) : ValidationRule {
    override fun validate(fieldName: String, value: Any?): ValidationError? {
        return if (value is String && !pattern.matches(value)) {
            ValidationError(fieldName, "$fieldName does not match the required pattern")
        } else null
    }
}