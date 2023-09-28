package com.example.hexagonalex.person.adapter.db

import com.example.hexagonalex.person.domain.FirstName
import com.example.hexagonalex.person.domain.LastName
import com.example.hexagonalex.person.domain.Person
import com.example.hexagonalex.person.domain.PersonId
import com.example.hexagonalex.person.domain.port.db.PersonRepository
import org.springframework.stereotype.Repository

@Repository
class PersonDbRepository(private val personSpringRepository: PersonSpringRepository): PersonRepository {

    // circuir breaker
    // retry
    // timeout
    // whatever
    override fun getAll(): List<Person> =
        personSpringRepository.findAll()
            .map { it.toDomain() }

    override fun findById(id: PersonId): Person? =
        personSpringRepository.findById(id.id)
            ?.toDomain()

    override fun create(person: Person): Person =
        personSpringRepository.save(person.toEntity())
            .toDomain()

    override fun existByFirstAndLastName(firstName: FirstName, lastName: LastName): Boolean =
        personSpringRepository.findFirstByFirstNameAndLastName(firstName.raw, lastName.raw) != null

    private fun Person.toEntity() = PersonEntity(
        id.id, firstName.raw, lastName.raw,age
    )

    private fun PersonEntity.toDomain() =
        Person(
            PersonId(id), FirstName(firstName), LastName(lastName), age
        )
}