package com.tinmegali.poc

import com.tinmegali.poc.avro.ProducerAvro
import com.tinmegali.poc.schema.avro.Event
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Post
import jakarta.inject.Singleton
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.util.*

@Singleton
@Controller("/api/sample")
class SampleController(
    private val producerAvro: ProducerAvro,
) {
    private val logger: Logger = LoggerFactory.getLogger(SampleController::class.java)

    @Post("/simple_event")
    fun publishSimpleEvent(event: Event) {
        val key = UUID.randomUUID().toString()
        logger.info("Publishing k: $key | event: $event")
        producerAvro.sendMessage(UUID.randomUUID().toString(), event)
    }

}
