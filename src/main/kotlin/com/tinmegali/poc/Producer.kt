package com.tinmegali.poc

import com.tinmegali.poc.schema.avro.Event
import io.micronaut.configuration.kafka.annotation.KafkaClient
import io.micronaut.configuration.kafka.annotation.KafkaKey
import io.micronaut.configuration.kafka.annotation.Topic
import io.micronaut.context.annotation.Factory

@Factory
@KafkaClient
interface Producer {

    @Topic("events")
    fun sendMessage(@KafkaKey id: String, event: Event)
}
