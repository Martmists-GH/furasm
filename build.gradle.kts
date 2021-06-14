plugins {
    kotlin("jvm") version "1.5.10"
    application
    idea
}

group = "dev.maow"
version = "1.1.1"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
}

tasks {
    compileKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
}

application.mainClass.set("dev.maow.furasm.Main")

distributions {
    main {
        contents {
            from("scripts") {
                include("*.fur")
                into("bin/scripts")
            }
        }
    }
}