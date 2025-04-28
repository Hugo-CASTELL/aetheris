group = "dev.aetheris"
version = "1.0-SNAPSHOT"

plugins {
    id("java")
}

repositories {
    mavenCentral()
    maven {
        name = "papermc"
        url = uri("https://repo.papermc.io/repository/maven-public/")
    }
}

dependencies {
    compileOnly("io.papermc.paper:paper-api:1.21.1-R0.1-SNAPSHOT")
    implementation("org.xerial:sqlite-jdbc:3.43.2.0")
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(21))
}