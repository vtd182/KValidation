package org.example.core
class ValidationContext {
    private val rules = mutableListOf<ValidationRule>()

    fun addRule(rule: ValidationRule): ValidationContext {
        rules.add(rule)
        return this
    }

    fun validate(value: Any?): List<String> {
        return rules.mapNotNull { it.validate(value) }
    }
}
