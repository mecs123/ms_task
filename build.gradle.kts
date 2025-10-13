plugins {
	java
	id("org.springframework.boot") version "3.5.6"
	id("io.spring.dependency-management") version "1.1.7"
	id("org.sonarqube") version "4.4.1.3373"
}

group = "com.task.api"
version = "0.0.1-SNAPSHOT"
description = "Description: Task microservice 001 - CRUD básico con WebFlux y Postgres"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(17)
	}
}

configurations {
	compileOnly {
		extendsFrom(configurations.annotationProcessor.get())
	}
}

repositories {
	mavenCentral()
}

dependencies {

	implementation("org.springframework.boot:spring-boot-starter-data-r2dbc")
	implementation("org.springframework.boot:spring-boot-starter-webflux")
	// Driver R2DBC oficial
	implementation("org.postgresql:r2dbc-postgresql:1.0.5.RELEASE")
	implementation("org.mapstruct:mapstruct:1.6.0")
	annotationProcessor("org.mapstruct:mapstruct-processor:1.6.0")

	compileOnly("org.projectlombok:lombok")
	runtimeOnly("org.postgresql:postgresql")
	runtimeOnly("org.postgresql:r2dbc-postgresql")
	annotationProcessor("org.projectlombok:lombok")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("io.projectreactor:reactor-test")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.withType<Test> {
	useJUnitPlatform()
}
