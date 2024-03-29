plugins {
    kotlin("jvm") version "1.9.0"
}

group = "kr.cosine.kiosk"
version = "1.0.0"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}