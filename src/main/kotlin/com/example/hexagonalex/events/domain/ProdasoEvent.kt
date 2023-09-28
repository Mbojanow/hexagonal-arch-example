package com.example.hexagonalex.events.domain

enum class EventType {
    DB, REST, GRAPHQL
}

data class ProdasoEvent(
    val type: EventType,
    val topic: String,
    val data: Map<String, String>
)