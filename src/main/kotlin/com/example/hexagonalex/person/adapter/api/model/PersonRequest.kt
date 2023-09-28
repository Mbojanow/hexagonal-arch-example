package com.example.hexagonalex.person.adapter.api.model

import jakarta.validation.constraints.NotBlank

data class PersonRequest(
    @NotBlank(message = "blablablba")
    val fn: String,
    val ln: String,
    val age: String
)