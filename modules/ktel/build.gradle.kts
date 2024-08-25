val ktelGroupId = "com.sicklibs"
val ktelName = "ktel"
val ktelVersion = "0.0.1"

description = "Patties :: $ktelName"
group = ktelGroupId
version = ktelVersion

plugins {
  `maven-publish`
  kotlin("jvm")
  kotlin("plugin.spring")
  groovy
  jacoco
}

repositories {
  mavenCentral()
}

dependencies {
  implementation("org.springframework.boot:spring-boot:3.3.3")
  implementation("org.jetbrains.kotlin:kotlin-reflect")
  implementation(project(":commons"))

  testImplementation("org.apache.groovy:groovy-all:4.0.22")
  testImplementation("org.spockframework:spock-core:2.4-M4-groovy-4.0")
  testImplementation("org.mockito:mockito-core:5.12.0")
  testImplementation(project(":commons-test"))
}

publishing {
  publications {
    create<MavenPublication>("mavenJava") {
      from(components["java"])

      groupId = ktelGroupId
      artifactId = ktelName
      version = ktelVersion
    }
  }
  repositories {
    mavenLocal()
  }
}

tasks.check {
  dependsOn(tasks.jacocoTestCoverageVerification)
}

tasks.test {
  useJUnitPlatform()
  finalizedBy(tasks.jacocoTestReport)
}

val excludedFromJacoco = listOf(
  "**/KtelApplication**"
)

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