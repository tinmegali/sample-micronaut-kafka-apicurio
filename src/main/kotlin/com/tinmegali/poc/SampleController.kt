package com.tinmegali.poc

import com.tinmegali.poc.avro.ProducerAvro
import com.tinmegali.poc.proto.ProducerProto
import com.tinmegali.poc.schema.avro.Event
import com.tinmegali.poc.schema.proto.HelloProto
import io.micronaut.core.annotation.Introspected
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
    private val producerProto: ProducerProto
) {
    private val logger: Logger = LoggerFactory.getLogger(SampleController::class.java)

    @Post("/simple_event")
    fun publishSimpleEvent(event: Event) {
        val key = UUID.randomUUID().toString()
        logger.info("Publishing k: $key | event: $event")
        producerAvro.sendMessage(UUID.randomUUID().toString(), event)
    }

    @Post("/simple_proto")
    fun publishSimpleProto(dto: HelloProtoDto) {
        logger.info("Publishing helloProto: $dto")
        val proto = HelloProto.newBuilder().setName(dto.name).build()
        producerProto.sendMessage(UUID.randomUUID().toString(), proto)
    }

}

@Introspected
data class HelloProtoDto(
    val name: String
)
