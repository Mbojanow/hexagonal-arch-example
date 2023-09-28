package com.example.hexagonalex.person.adapter.db

import jakarta.persistence.Entity
import jakarta.persistence.Id
import java.util.*

@Entity
data class PersonEntity(
    @Id val id: UUID,
    val firstName: String,
    val lastName: String,
    val age: Int
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as PersonEntity

        return id == other.id
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }

    override fun toString(): String {
        return "PersonEntity(id=$id)"
    }
}