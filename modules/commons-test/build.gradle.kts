description = "Patties :: commons-test"
group = "com.sicklibs"
version = "0.0.1"

plugins {
  groovy
}

repositories {
  mavenCentral()
}

dependencies {
  implementation("org.apache.groovy:groovy-all:4.0.22")
  implementation("org.spockframework:spock-core:2.4-M4-groovy-4.0")
  implementation("org.mockito:mockito-core:5.12.0")
}