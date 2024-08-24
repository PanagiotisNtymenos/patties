description = "Patties :: ktel"
group = "com.sicklibs"
version = "0.0.1"

plugins {
  kotlin("jvm")
  kotlin("plugin.spring")
  id("org.springframework.boot") version "3.3.3"
  id("io.spring.dependency-management") version "1.1.6"
  groovy
}

repositories {
  mavenCentral()
}

dependencies {
  implementation("org.springframework.boot:spring-boot-starter")
  implementation("org.jetbrains.kotlin:kotlin-reflect")
  implementation(project(":commons"))

  testImplementation("org.apache.groovy:groovy-all:4.0.22")
  testImplementation("org.spockframework:spock-core:2.4-M4-groovy-4.0")
  testImplementation("org.mockito:mockito-core:5.12.0")
  testImplementation(project(":commons-test"))
}

tasks.test {
  useJUnitPlatform()
}