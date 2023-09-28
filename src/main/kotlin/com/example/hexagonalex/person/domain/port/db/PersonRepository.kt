package com.example.hexagonalex.person.domain.port.db

import com.example.hexagonalex.person.domain.FirstName
import com.example.hexagonalex.person.domain.LastName
import com.example.hexagonalex.person.domain.Person
import com.example.hexagonalex.person.domain.PersonId

interface PersonRepository {
    fun getAll(): List<Person>

    fun findById(id: PersonId): Person?

    fun create(person: Person): Person

    fun existByFirstAndLastName(firstName: FirstName, lastName: LastName): Boolean
}