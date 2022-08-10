package com.tinmegali.poc.avro

import com.tinmegali.poc.schema.avro.Event
import io.micronaut.configuration.kafka.annotation.KafkaKey
import io.micronaut.configuration.kafka.annotation.KafkaListener
import io.micronaut.configuration.kafka.annotation.Topic
import jakarta.inject.Singleton
import org.slf4j.LoggerFactory

@Singleton
@KafkaListener(
    groupId = "avro",
    clientId = "avro"
)
class ConsumerAvro {

    private val logger = LoggerFactory.getLogger(ConsumerAvro::class.java)

    @Topic("events")
    fun receive(@KafkaKey id: String, event: Event) {
        logger.info("Received message topic [events]: [$id] - $event")
    }
}
