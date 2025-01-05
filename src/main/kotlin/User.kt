package org.example

import org.example.annotations.Min
import org.example.annotations.NotEmpty
import org.example.annotations.Regex
import org.example.core.ValidatableObject

data class User(
    @NotEmpty
    val name: String,

    @Min(18)
    val age: Int,

    @Regex("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z|a-z]{2,}\$")
    val email: String
) : ValidatableObject()