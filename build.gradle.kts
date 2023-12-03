import org.gradle.api.tasks.testing.logging.TestExceptionFormat

plugins {
  id("org.jetbrains.kotlin.jvm") version "1.9.+"
  id("com.ncorti.ktfmt.gradle") version "0.15.+"
}

repositories {
  mavenCentral()
}

dependencies {
  testImplementation(kotlin("test"))
  testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

sourceSets {
  val test by getting
  test.kotlin.srcDirs("src")
}

ktfmt {
  googleStyle()
}

tasks {
  test {
    useJUnitPlatform()
    dependsOn("ktfmtFormat")
    testLogging {
      events("PASSED", "SKIPPED", "FAILED")
      showStackTraces = false
      showStandardStreams = true
      exceptionFormat = TestExceptionFormat.FULL
    }
  }
}

kotlin {
  jvmToolchain(17)
}
