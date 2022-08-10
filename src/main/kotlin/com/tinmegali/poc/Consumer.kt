package com.tinmegali.poc

import com.tinmegali.poc.schema.avro.Event
import io.micronaut.configuration.kafka.annotation.KafkaKey
import io.micronaut.configuration.kafka.annotation.KafkaListener
import io.micronaut.configuration.kafka.annotation.Topic
import jakarta.inject.Singleton
import org.slf4j.LoggerFactory

@Singleton
@KafkaListener
class Consumer {

    private val logger = LoggerFactory.getLogger(Consumer::class.java)

    @Topic("events")
    fun receive(@KafkaKey id: String, event: Event) {
        logger.info("Received message topic [events]: [$id] - $event")
    }
}
