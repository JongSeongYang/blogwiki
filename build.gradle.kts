import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.jetbrains.kotlin.ir.backend.js.compile
import org.jetbrains.kotlin.kapt3.base.Kapt.kapt

plugins {
	id("org.springframework.boot") version "2.5.9"
	id("io.spring.dependency-management") version "1.0.11.RELEASE"
	kotlin("jvm") version "1.5.32"
	kotlin("kapt") version "1.5.32"
	kotlin("plugin.spring") version "1.5.32"
	kotlin("plugin.jpa") version "1.5.32"
}

sourceSets["main"].withConvention(org.jetbrains.kotlin.gradle.plugin.KotlinSourceSet::class) {
	kotlin.srcDir("$buildDir/generated/source/kapt/main")
}

group = "com.hiandd"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11

repositories {
	mavenCentral()
}
allOpen {
	annotation("org.springframework.data.mongodb.core.mapping.Document")
	annotation("javax.persistence.Entity")
	annotation("javax.persistence.MappedSuperclass")
	annotation("javax.persistence.Embeddable")
}


dependencies {
	// db
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-data-mongodb")
	// querydsl
	api("com.querydsl:querydsl-jpa:4.4.0")
	implementation("com.querydsl:querydsl-mongodb:4.4.0")
	kapt("com.querydsl:querydsl-apt:4.2.2:jpa")
	kapt("org.hibernate.javax.persistence:hibernate-jpa-2.1-api:1.0.2.Final")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

	testImplementation("org.springframework.boot:spring-boot-starter-test")
	// swagger
	implementation ("io.springfox:springfox-boot-starter:3.0.0")
	implementation("io.springfox:springfox-swagger-ui:3.0.0")
	// okhttp
	implementation("com.squareup.okhttp3", "okhttp", "4.2.2")
	// jwtToken
	implementation ("io.jsonwebtoken:jjwt-api:0.11.2")
	runtimeOnly ("io.jsonwebtoken:jjwt-impl:0.11.2", "io.jsonwebtoken:jjwt-jackson:0.11.2")
	// model mapper
	implementation("org.modelmapper:modelmapper:2.4.4")
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
