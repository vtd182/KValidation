package org.example.core

interface ValidationRule {
    fun validate(fieldName: String, value: Any?): ValidationError?
}

// list anootation``annotation
