package com.example.hexagonalex.person.adapter.service

import com.example.hexagonalex.person.domain.Person
import com.example.hexagonalex.person.domain.PersonId
import com.example.hexagonalex.person.domain.port.service.PersonCrudService
import com.example.hexagonalex.person.domain.port.db.PersonRepository
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class PersonCrudServiceAdapter(private val personRepository: PersonRepository): PersonCrudService {
    override fun getAllPersons(): List<Person> =
        personRepository.getAll()

    @Cacheable(cacheNames = ["thisisverycacheishandled"])
    override fun getPersonById(id: PersonId): Person =
        personRepository.findById(id) ?: // send event, notify,
        throw RuntimeException("")

    @Transactional
    override fun createPerson(person: Person): Person =
        if (personRepository.existByFirstAndLastName(person.firstName, person.lastName)) {
            throw RuntimeException("boom")
        } else {
            personRepository.create(person)
        }
}