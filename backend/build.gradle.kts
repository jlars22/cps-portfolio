plugins {
    java
    id("org.springframework.boot") version "3.2.3"
    id("io.spring.dependency-management") version "1.1.4"
    id("com.diffplug.spotless") version "6.25.0"
}

group = "org.cpsportfolio"
version = "0.0.1-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_17
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation ("org.springframework.boot:spring-boot-starter-web")
    implementation ("org.springframework.boot:spring-boot-starter-data-mongodb")

    compileOnly("org.projectlombok:lombok:1.18.30")
    annotationProcessor("org.projectlombok:lombok:1.18.30")

    testCompileOnly("org.projectlombok:lombok:1.18.30")
    testAnnotationProcessor("org.projectlombok:lombok:1.18.30")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.withType<Test> {
    useJUnitPlatform()
}

spotless {
    java {
        toggleOffOn()
        prettier(
            mapOf(
                "prettier" to "3.0.3",
                "prettier-plugin-java" to "2.3.0"
            )
        ).config(
            mapOf(
                "parser" to "java",
                "printWidth" to 100,
                "tabWidth" to 4,
                "plugins" to listOf("prettier-plugin-java")
            )
        )
        removeUnusedImports()
        trimTrailingWhitespace()
        endWithNewline()
    }
}

