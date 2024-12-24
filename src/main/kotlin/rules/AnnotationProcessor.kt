package org.example.rules

import org.example.annotations.Regex
import org.example.annotations.Min
import org.example.annotations.NotEmpty
import org.example.core.ValidationRule

class AnnotationProcessor {
    fun processAnnotations(obj: Any): List<ValidationRule> {
        val rules = mutableListOf<ValidationRule>()
        val fields = obj::class.java.declaredFields

        for (field in fields) {
            field.isAccessible = true
            val annotations = field.annotations
            for (annotation in annotations) {
                when (annotation) {
                    is NotEmpty -> rules.add(NotEmptyRule())
                    is Regex -> rules.add(RegexRule(annotation.pattern.toRegex()))
                    is Min -> rules.add(MinRule(annotation.value))
                }
            }
        }
        return rules
    }
}