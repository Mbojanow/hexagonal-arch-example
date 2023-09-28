package com.example.hexagonalex.person.adapter.api

import com.example.hexagonalex.person.adapter.api.model.PersonRequest
import com.example.hexagonalex.person.adapter.api.model.PersonResponse
import com.example.hexagonalex.person.adapter.api.model.PersonsResponse
import com.example.hexagonalex.person.domain.FirstName
import com.example.hexagonalex.person.domain.LastName
import com.example.hexagonalex.person.domain.Person
import com.example.hexagonalex.person.domain.PersonId
import com.example.hexagonalex.person.domain.port.service.PersonCrudService
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.net.URI
import java.util.UUID

@RestController
@RequestMapping("/api/persons")
class PersonController(private val personCrudService: PersonCrudService) {

    @GetMapping
    fun findAllUsers(): PersonsResponse =
        personCrudService.getAllPersons()
            .map { it.toResponse() }
            .let { PersonsResponse(it) }

    @GetMapping("/{id}")
    fun findUser(@PathVariable id: UUID): PersonResponse =
        personCrudService.getPersonById(PersonId(id))
            .toResponse()

    @PostMapping
    fun createUser(@Valid personRequest: PersonRequest): ResponseEntity<PersonResponse> {
        val createPerson = personCrudService.createPerson(personRequest.toDomain())
        return ResponseEntity.created(URI.create("/${createPerson.id.id}"))
            .body(createPerson.toResponse())
    }


    private fun PersonRequest.toDomain() = Person(PersonId(UUID.randomUUID()), FirstName(fn), LastName(ln), age.toInt())

    private fun Person.toResponse() =
        PersonResponse(firstName.raw, lastName.raw, age)
}