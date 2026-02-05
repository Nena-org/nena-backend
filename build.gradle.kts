plugins {
    id("java")
    id("org.springframework.boot") version "3.4.2"
    id("io.spring.dependency-management") version "1.1.7"
    id("com.diffplug.spotless") version "8.1.0"
}

group = "com"
version = "1.0-SNAPSHOT"
description = "back"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

spotless {
    java {
        target(
            "src/main/java/**/*.java",
            "src/test/java/**/*.java"
        )

        palantirJavaFormat()
        removeUnusedImports()
        trimTrailingWhitespace()
        endWithNewline()
        formatAnnotations()

        importOrder("java", "javax", "org", "com", "")
    }

    format("yaml") {
        target("**/*.yml", "**/*.yaml")
        trimTrailingWhitespace()
        endWithNewline()
    }

    format("misc") {
        target("*.gradle", "*.gradle.kts", "*.md", ".gitignore")
        trimTrailingWhitespace()
        endWithNewline()
    }
}

repositories {
    mavenCentral()
}

dependencies {
    // Spring Boot
    implementation("org.springframework.boot:spring-boot-starter-web") // ✅ webmvc 대신
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    //implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.boot:spring-boot-starter-data-redis")
    implementation("org.springframework.boot:spring-boot-starter-batch")

    // Security crypto
    implementation("org.springframework.security:spring-security-crypto")

    // DB
    runtimeOnly("com.h2database:h2")

    // Dev tools
    compileOnly("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok")
    developmentOnly("org.springframework.boot:spring-boot-devtools")

    // Test
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework.batch:spring-batch-test")

    // Swagger
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.5.0")
}

tasks.named("compileJava") {
    dependsOn("spotlessApply")
}
tasks.named("compileTestJava") {
    dependsOn("spotlessApply")
}

tasks.test {
    useJUnitPlatform()
}
