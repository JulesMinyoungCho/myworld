import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    // Spring Default
    id("org.springframework.boot") version "2.6.3"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
    // Kotlin
    kotlin("jvm") version "1.6.10"
    kotlin("plugin.spring") version "1.6.10"
    // Kotlin JPA
    kotlin("plugin.jpa") version "1.6.10"
    kotlin("plugin.allopen") version "1.6.10"
    kotlin("kapt") version "1.6.10"
    idea
}

group = "com.my"
version = "demo"
java.sourceCompatibility = JavaVersion.VERSION_11

repositories {
    mavenCentral()
}
dependencies {
    // Kotlin
    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

    // spring core
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-aop")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    // Mybatis
    implementation("org.mybatis.spring.boot:mybatis-spring-boot-starter:2.2.2")

    // test + mockk
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation ("io.mockk:mockk:1.10.2")

    // jwt
    implementation ("io.jsonwebtoken:jjwt:0.9.1")

    // mysql connector
    implementation ("mysql:mysql-connector-java:8.0.21")

    // swagger
    implementation("io.springfox:springfox-boot-starter:3.0.0"){
        exclude(group = "org.mapstruct", module = "mapstruct")
    }
    implementation("io.springfox:springfox-swagger-ui:3.0.0")

    // mapstruct
    implementation("org.mapstruct:mapstruct:1.4.1.Final")
    kapt("org.mapstruct:mapstruct-processor:1.4.1.Final")

    // Google collections
    implementation("com.google.guava:guava:23.0")

    // queryDsl
    kapt("jakarta.annotation:jakarta.annotation-api")
    kapt("jakarta.persistence:jakarta.persistence-api")
    kapt("org.springframework.boot:spring-boot-configuration-processor")
    implementation("com.querydsl:querydsl-jpa:5.0.0")
    kapt("com.querydsl:querydsl-apt::jpa")
    // H2 DB
    runtimeOnly("com.h2database:h2")

}
tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "11"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}
configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}
// QueryDsl setting
idea {
    module {
        val kaptMain = file("build/generated/source/kapt/main")
        sourceDirs.add(kaptMain)
        generatedSourceDirs.add(kaptMain)
    }
}
