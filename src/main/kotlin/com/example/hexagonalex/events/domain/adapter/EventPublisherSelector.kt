package com.example.hexagonalex.events.domain.adapter

import com.example.hexagonalex.events.domain.EventType
import com.example.hexagonalex.events.domain.ProdasoEvent
import com.example.hexagonalex.events.domain.port.EventPublisher
import org.springframework.stereotype.Component
import java.lang.IllegalArgumentException

@Component
class EventPublisherSelector(private val publishers: List<InternalEventPublisher>): EventPublisher {
    override fun publish(event: ProdasoEvent) {
        publishers.firstOrNull { it.isSupported(event.type) }?.publish(event)
            ?: IllegalArgumentException("${event.type} is not supported}")
    }
}

interface InternalEventPublisher {
    fun publish(event: ProdasoEvent)
    fun isSupported(eventType: EventType): Boolean
}

@Component
class DbEventPublisher: InternalEventPublisher {
    override fun publish(event: ProdasoEvent) {
        println("Sending db event")
    }

    override fun isSupported(eventType: EventType): Boolean =
        eventType == EventType.DB
}

@Component
class RestEventPublisher: InternalEventPublisher {
    override fun publish(event: ProdasoEvent) {
        println("Sending rest event")
    }

    override fun isSupported(eventType: EventType): Boolean =
        eventType == EventType.REST
}

@Component
class GraphqlEventPublisher: InternalEventPublisher {
    override fun publish(event: ProdasoEvent) {
        println("Sending graphql event")
    }

    override fun isSupported(eventType: EventType): Boolean =
        eventType == EventType.GRAPHQL
}
