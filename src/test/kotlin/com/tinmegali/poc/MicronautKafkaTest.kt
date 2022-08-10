package com.tinmegali.poc

import io.micronaut.runtime.EmbeddedApplication
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import kotlin.test.Test

@MicronautTest
class MicronautKafkaTest(private val application: EmbeddedApplication<*>) {

    @Test
    fun basicTest() {
        assert(application.isRunning)
    }
}
