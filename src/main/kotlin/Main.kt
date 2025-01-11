package org.example

import org.example.annotations.Min
import org.example.annotations.NotEmpty
import org.example.annotations.Regex
import org.example.core.ValidatableObject
import org.example.core.ValidationException

data class User(
    @NotEmpty
    val name: String,

    @Min(18)
    val age: Int,

    @Regex("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z|a-z]{2,}\$")
    val email: String
) : ValidatableObject()


fun main() {
    try {
        val user = User(name = "", age = 16, email = "invalid-email")
        println(user)
    } catch (e: ValidationException) {
        println("haha con cho ngu")
        println(e)
    }
}
