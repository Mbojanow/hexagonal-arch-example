package com.example.hexagonalex.events.domain.port

import com.example.hexagonalex.events.domain.ProdasoEvent

interface EventPublisher {
    fun publish(event: ProdasoEvent) //
}