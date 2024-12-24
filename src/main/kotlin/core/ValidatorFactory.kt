package org.example.core

class ValidatorFactory {
    fun createValidator(rules: List<ValidationRule>): Validator = ValidatorImpl(rules)
}

class ValidatorImpl(private val rules: List<ValidationRule>) : Validator {
    override fun validate(obj: Any): ErrorMap {
        val errorMap = mutableMapOf<String, MutableList<ValidationError>>()
        val fields = obj::class.java.declaredFields

        for (field in fields) {
            field.isAccessible = true
            val value = field.get(obj)
            val fieldName = field.name

            rules.forEach { rule ->
                val error = rule.validate(fieldName, value)
                if (error != null) {
                    errorMap.computeIfAbsent(fieldName) { mutableListOf() }.add(error)
                }
            }
        }
        return errorMap
    }

    override fun validateField(fieldName: String, value: Any): ErrorMap {
        val errorMap = mutableMapOf<String, MutableList<ValidationError>>()
        rules.forEach { rule ->
            val error = rule.validate(fieldName, value)
            if (error != null) {
                errorMap.computeIfAbsent(fieldName) { mutableListOf() }.add(error)
            }
        }
        return errorMap
    }
}