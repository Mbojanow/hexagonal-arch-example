package com.example.hexagonalex.person.adapter.api.model

data class PersonsResponse(
    val people: List<PersonResponse>
)

data class PersonResponse(
    val fn: String,
    val ln: String,
    val age: Int
)