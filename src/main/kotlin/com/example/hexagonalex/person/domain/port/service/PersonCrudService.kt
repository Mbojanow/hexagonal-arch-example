package com.example.hexagonalex.person.domain.port.service

import com.example.hexagonalex.person.domain.Person
import com.example.hexagonalex.person.domain.PersonId

interface PersonCrudService {
    fun getAllPersons(): List<Person>

    fun getPersonById(id: PersonId): Person

    fun createPerson(person: Person): Person
}