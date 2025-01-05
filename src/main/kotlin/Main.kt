package org.example

import org.example.core.ValidationException
import org.example.rules.AnnotationProcessor


fun validateWithAnnotations(user: User) {
    val processor = AnnotationProcessor()
    val rules = processor.processAnnotations(user)
    val validator = org.example.core.ValidatorFactory().createValidator(rules)

    val errors = validator.validate(user)

    if (errors.isNotEmpty()) {
        throw ValidationException(errors)
    } else {
        println("Validation passed successfully!")
    }
}

fun main() {
    try {
        val user = User(name = "", age = 16, email = "invalid-email")
        println(user)
    } catch (e: ValidationException) {
        println("haha con cho ngu")
        println(e)
    }
}
