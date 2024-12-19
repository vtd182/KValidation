package org.example.annotations
@Target(AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
annotation class NotEmpty(val message: String = "Field cannot be empty")
