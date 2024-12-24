package org.example.core

class ValidationException(val errors: ErrorMap) : RuntimeException() {
    override val message: String
        get() = buildString {
            append("Validation failed with the following errors:\n")
            errors.forEach { (field, errors) ->
                append("Field '$field':\n")
                errors.forEach { error ->
                    append("  - ${error.message}\n")
                }
            }
        }
}