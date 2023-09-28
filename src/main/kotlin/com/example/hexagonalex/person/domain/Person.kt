package com.example.hexagonalex.person.domain

import java.util.*

data class PersonId(
    val id: UUID
)

data class FirstName(
    val raw: String
)

data class LastName(
    val raw: String
)

data class Person(
    val id: PersonId,
    val firstName: FirstName,
    val lastName: LastName,
    val age: Int
)