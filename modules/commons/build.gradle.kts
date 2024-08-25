description = "Patties :: commons"

plugins {
  kotlin("jvm")
  groovy
  jacoco
}

repositories {
  mavenCentral()
}

dependencies {
  implementation("org.jetbrains.kotlin:kotlin-reflect")

  testImplementation("org.apache.groovy:groovy-all:4.0.22")
  testImplementation("org.spockframework:spock-core:2.4-M4-groovy-4.0")
  testImplementation("org.mockito:mockito-core:5.12.0")
  testImplementation(project(":commons-test"))
}

tasks.check {
  dependsOn(tasks.jacocoTestCoverageVerification)
}

tasks.test {
  useJUnitPlatform()
  finalizedBy(tasks.jacocoTestReport)
}

val excludedFromJacoco = emptyList<String>()

tasks.jacocoTestReport {
  reports {
    xml.required.set(true)
    html.required.set(true)
  }

  classDirectories.setFrom(
    files(
      classDirectories.files.map {
        fileTree(it) {
          exclude(excludedFromJacoco)
        }
      }
    )
  )

  dependsOn(tasks.test)
}

tasks.jacocoTestCoverageVerification {
  classDirectories.setFrom(
    files(
      classDirectories.files.map {
        fileTree(it) {
          exclude(excludedFromJacoco)
        }
      }
    )
  )

  violationRules {
    rule {
      element = "CLASS"

      limit {
        counter = "INSTRUCTION"
        value = "COVEREDRATIO"
        minimum = 1.toBigDecimal()
      }
      limit {
        counter = "BRANCH"
        value = "COVEREDRATIO"
        minimum = 1.toBigDecimal()
      }
    }
  }
}