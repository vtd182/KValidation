package org.example.core
import kotlin.reflect.KClass
import kotlin.reflect.full.findAnnotation

interface Validator {
    fun validate(obj: Any): ErrorMap
    fun validateField(fieldName: String, value: Any): ErrorMap
}
