package org.example.core

import org.example.annotations.Email
import org.example.annotations.NotEmpty
import kotlin.reflect.KClass
import kotlin.reflect.full.findAnnotation

class Validator {

    fun validate(obj: Any): List<String> {
        val errors = mutableListOf<String>()
        val kClass: KClass<out Any> = obj::class

        kClass.members.forEach { member ->
            val value = member.call(obj)

            // Process NotEmpty annotation
            member.findAnnotation<NotEmpty>()?.let { annotation ->
                if (value == null || value.toString().isEmpty()) {
                    errors.add(annotation.message)
                }
            }

            // Process Email annotation
            member.findAnnotation<Email>()?.let { annotation ->
                if (value == null || !isValidEmail(value.toString())) {
                    errors.add(annotation.message)
                }
            }
        }

        return errors
    }

    private fun isValidEmail(email: String): Boolean {
        val regex = "[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}".toRegex()
        return regex.matches(email)
    }
}
