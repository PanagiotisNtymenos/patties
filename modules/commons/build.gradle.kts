description = "Patties :: commons"
group = "com.sicklibs"
version = "0.0.1"

plugins {
  kotlin("jvm")
  groovy
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

tasks.test {
  useJUnitPlatform()
}