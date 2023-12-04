plugins {
    kotlin("jvm") version "1.9.0"
}

group = "kennarddh"
version = "1.0"

repositories {
    mavenCentral()
    maven("https://www.jitpack.io")
}

val mindustryVersion by extra { "v146" }
val jabelVersion by extra { "93fde537c7" }

dependencies {
    testImplementation(kotlin("test"))

    compileOnly("com.github.Anuken.Arc:arc-core:$mindustryVersion")
    compileOnly("com.github.Anuken.Mindustry:core:$mindustryVersion")
    annotationProcessor("com.github.Anuken:jabel:$jabelVersion")
}


tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(17)
}

java {
    targetCompatibility = JavaVersion.VERSION_17
    sourceCompatibility = JavaVersion.VERSION_17
}

tasks.register<Jar>("buildJAR") {
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE

    val contents = configurations.runtimeClasspath.get().map { if (it.isDirectory) it else zipTree(it) }

    from(contents)
    from(rootProject.fileTree("src/main/resources/"))
}
