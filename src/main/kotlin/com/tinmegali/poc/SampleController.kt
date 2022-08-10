package com.tinmegali.poc

import com.tinmegali.poc.schema.avro.Event
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Post
import jakarta.inject.Singleton
import org.apache.kafka.clients.producer.ProducerConfig
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.util.UUID

@Singleton
@Controller("/api/sample")
class SampleController(
    private val producer: Producer
) {
    private val logger: Logger = LoggerFactory.getLogger(SampleController::class.java)

    @Post("/simple_event")
    fun publishSimpleEvent(event: Event) {
        val key = UUID.randomUUID().toString()
        logger.info("Publishing k: $key | event: $event")
        producer.sendMessage(UUID.randomUUID().toString(), event)
        ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG
    }

}
