package com.tinmegali.poc.proto

import com.tinmegali.poc.schema.proto.HelloProto
import io.micronaut.configuration.kafka.annotation.KafkaKey
import io.micronaut.configuration.kafka.annotation.KafkaListener
import io.micronaut.configuration.kafka.annotation.Topic
import jakarta.inject.Singleton
import org.slf4j.LoggerFactory

@Singleton
@KafkaListener(
    groupId = "proto",
    clientId = "proto"
)
class ConsumerProto {

    private val logger = LoggerFactory.getLogger(ConsumerProto::class.java)

    @Topic("sample_proto")
    fun receive(@KafkaKey id: String, event: HelloProto) {
        logger.info("Received message topic [sample_proto]: [$id] - $event")
    }
}
