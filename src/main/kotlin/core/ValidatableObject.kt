package org.example.core

import org.example.rules.AnnotationProcessor

abstract class ValidatableObject {
	init {
		val processor = AnnotationProcessor()
		val rules = processor.processAnnotations(this)
		val validator = ValidatorFactory().createValidator(rules)
		val errors = validator.validate(this)
		if (errors.isNotEmpty()) {
			throw ValidationException(errors)
		}
	}
}