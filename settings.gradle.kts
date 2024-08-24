rootProject.name = "patties"

include(":commons")
project(":commons").projectDir = file("modules/commons")

include(":commons-test")
project(":commons-test").projectDir = file("modules/commons-test")

include(":ktel")
project(":ktel").projectDir = file("modules/ktel")
