package com.tinmegali.poc.proto

import com.tinmegali.poc.schema.proto.HelloProto
import io.micronaut.configuration.kafka.annotation.KafkaClient
import io.micronaut.configuration.kafka.annotation.KafkaKey
import io.micronaut.configuration.kafka.annotation.Topic
import io.micronaut.context.annotation.Factory

@Factory
@KafkaClient("proto")
interface ProducerProto {

    @Topic("sample_proto")
    fun sendMessage(@KafkaKey id: String, proto: HelloProto)
}
