import com.google.protobuf.gradle.generateProtoTasks
import com.google.protobuf.gradle.id
import com.google.protobuf.gradle.protobuf
import com.google.protobuf.gradle.protoc

plugins {
    id("org.jetbrains.kotlin.jvm") version "1.6.21"
    id("org.jetbrains.kotlin.kapt") version "1.6.21"
    id("org.jetbrains.kotlin.plugin.allopen") version "1.6.21"
    id("com.github.johnrengelman.shadow") version "7.1.2"
    id("io.micronaut.application") version "3.5.1"
    id("com.google.cloud.tools.jib") version "2.8.0"
    id("io.micronaut.test-resources") version "3.5.1"
    id("com.github.davidmc24.gradle.plugin.avro") version "1.3.0"
    id("com.google.protobuf") version "0.8.19"
    idea
}

version = "0.1"
group = "com.tinmegali.poc"

val kotlinVersion = project.properties["kotlinVersion"]
repositories {
    mavenCentral()
}

dependencies {
    kapt("io.micronaut:micronaut-http-validation")
    implementation("com.google.protobuf:protobuf-kotlin:3.21.4")
    implementation("com.google.protobuf:protobuf-java-util:3.21.4")
    implementation("io.micronaut:micronaut-http-client")
    implementation("io.micronaut:micronaut-jackson-databind")
    implementation("io.micronaut.kafka:micronaut-kafka")
    implementation("io.micronaut.kotlin:micronaut-kotlin-runtime")
    implementation("jakarta.annotation:jakarta.annotation-api")
    implementation("org.jetbrains.kotlin:kotlin-reflect:${kotlinVersion}")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:${kotlinVersion}")
    runtimeOnly("ch.qos.logback:logback-classic")
    implementation("io.micronaut:micronaut-validation")
    runtimeOnly("com.fasterxml.jackson.module:jackson-module-kotlin")

    // Avro
    implementation("org.apache.avro:avro:1.11.0")

    // Apicurio
    implementation("io.apicurio:apicurio-registry-client:2.2.5.Final")
    implementation("io.apicurio:apicurio-registry-utils-kafka:2.2.5.Final")
    implementation("io.apicurio:apicurio-registry-serdes-avro-serde:2.2.5.Final")
    implementation("io.apicurio:apicurio-registry-serdes-protobuf-serde:2.2.5.Final")


    // test
    testImplementation("org.jetbrains.kotlin:kotlin-test")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.6.4")
    testImplementation("io.projectreactor:reactor-test:3.4.21")
    testImplementation("org.testcontainers:testcontainers:1.17.3")
    testImplementation("com.redis.testcontainers:testcontainers-redis-junit:1.6.2")

}


application {
    mainClass.set("com.tinmegali.poc.ApplicationKt")
}
java {
    sourceCompatibility = JavaVersion.toVersion("17")
}

tasks.test {
    useJUnitPlatform()
}

tasks {
    compileKotlin {
        kotlinOptions {
            jvmTarget = "17"
        }
    }
    compileTestKotlin {
        kotlinOptions {
            jvmTarget = "17"
        }
    }
    jib {
        to {
            image = "gcr.io/myapp/jib-image"
        }
    }
}
graalvmNative.toolchainDetection.set(false)
micronaut {
    runtime("netty")
    testRuntime("junit5")
    processing {
        incremental(true)
        annotations("com.tinmegali.poc.*")
    }
    testResources {
        sharedServer.set(true)
    }
}

protobuf {
    protoc {
        artifact = "com.google.protobuf:protoc:3.21.4"
    }
    generateProtoTasks {
        all().forEach {
            it.builtins {
                id("kotlin")
            }
        }
    }
}



