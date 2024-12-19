package org.example.annotations
@Target(AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
annotation class Email(val message: String = "Invalid email format")
