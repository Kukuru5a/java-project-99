plugins {
    java
    application
    checkstyle
    jacoco
    id("org.springframework.boot") version "3.2.1"
    id("io.spring.dependency-management") version "1.1.4"
    id("io.freefair.lombok") version "8.3"
    id ("io.sentry.jvm.gradle") version "4.3.0"
}


group = "hexlet.code"
version = "0.0.1-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_20
}
application{
    mainClass = "hexlet.code.AppApplication"
}
repositories {
    mavenCentral()
}


dependencies {
    implementation("org.springframework.boot:spring-boot-starter")
    testImplementation("org.springframework.boot:spring-boot-starter-test")

    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.springframework.boot:spring-boot-devtools")
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.boot:spring-boot-configuration-processor")
    implementation("org.springframework.boot:spring-boot-starter-security")

    implementation("org.springframework.boot:spring-boot-starter-security:3.0.4")
    implementation("org.springframework.boot:spring-boot-starter-oauth2-resource-server:3.1.0")

    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.2.0")

    implementation("org.openapitools:jackson-databind-nullable:0.2.6")
    implementation("org.mapstruct:mapstruct:1.6.0.Beta1")
    annotationProcessor("org.mapstruct:mapstruct-processor:1.6.0.Beta1")

    implementation("org.instancio:instancio-junit:3.6.0")
    implementation("net.javacrumbs.json-unit:json-unit-assertj:3.2.2")
    implementation("net.datafaker:datafaker:2.0.2")

    runtimeOnly("com.h2database:h2:2.2.224")
    implementation("net.datafaker:datafaker:2.0.1")
    implementation("org.instancio:instancio-junit:3.3.0")
    implementation("net.javacrumbs.json-unit:json-unit-assertj:3.2.2")
    testImplementation("org.springframework.boot:spring-boot-starter-test:3.1.0")
    testImplementation("org.springframework.security:spring-security-test:6.0.2")
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter:5.10.0")
    testCompileOnly("org.projectlombok:lombok:1.18.30")
    testAnnotationProcessor("org.projectlombok:lombok:1.18.30")

    implementation("com.zaxxer:HikariCP:5.0.1")
}

tasks.withType<Test> {
    useJUnitPlatform()
}

sentry {
    val env = System.getenv("APP_ENV")
    if (env != null && env.contentEquals("prod")) {
        includeSourceContext = true
        org = "romaroma"
        projectName = "java-spring-boot"
        authToken = System.getenv("SENTRY_AUTH_TOKEN")
    }

}

tasks.jacocoTestReport {
    reports {
        xml.required = true
    }
}
