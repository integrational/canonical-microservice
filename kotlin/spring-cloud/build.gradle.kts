import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

group = "org.integrational.canms.kotlin.springcloud"
version = "0.0.1-SNAPSHOT"

val javaVersion = JavaVersion.VERSION_11
val springCloudVersion = "Hoxton.SR3"

tasks.wrapper {
    distributionType = Wrapper.DistributionType.ALL
}

plugins {
    val kotlinVersion = "1.3.72"

    kotlin("jvm") version kotlinVersion
    kotlin("plugin.spring") version kotlinVersion

    id("org.springframework.boot") version "2.2.6.RELEASE"
    id("io.spring.dependency-management") version "1.0.9.RELEASE"
}

fun springBoot(m: String) = "org.springframework.boot:spring-boot-$m"
fun springCloud(m: String) = "org.springframework.cloud:spring-cloud-$m"

repositories {
    mavenLocal() // for domain
    mavenCentral()
}

dependencyManagement {
    imports {
        mavenBom(springCloud("dependencies:$springCloudVersion"))
    }
}

dependencies {
    implementation(platform(kotlin("bom"))) // align Kotlin component versions
    implementation(kotlin("stdlib-jdk8"))
    implementation(kotlin("reflect"))

    implementation("javax.inject", "javax.inject", "1") // JSR-330: @Inject, @Singleton, @Named, ...

    implementation("org.integrational.canms.kotlin", "domain", "0.1.0")

    implementation(springCloud("stream"))

    testImplementation(springBoot("starter-test")) {
        exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
    }
    testImplementation(springCloud("stream-test-support"))
}

tasks.withType<Test> {
    useJUnitPlatform()
}

java {
    sourceCompatibility = javaVersion
    targetCompatibility = javaVersion
}

tasks.withType<KotlinCompile>().configureEach {
    kotlinOptions {
        allWarningsAsErrors = true
        javaParameters = true
        jvmTarget = javaVersion.toString()
        freeCompilerArgs = listOf("-Xjsr305=strict")
    }
}
