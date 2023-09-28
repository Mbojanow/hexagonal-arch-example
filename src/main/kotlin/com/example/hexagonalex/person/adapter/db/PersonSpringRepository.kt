package com.example.hexagonalex.person.adapter.db

import org.springframework.data.repository.Repository
import java.util.*

interface PersonSpringRepository: Repository<PersonEntity, UUID> {
    fun findAll(): List<PersonEntity>
    fun findById(id: UUID): PersonEntity?
    fun save(person: PersonEntity): PersonEntity
    fun findFirstByFirstNameAndLastName(firstName: String, lastName: String): PersonEntity?
}