buildscript {
    val kotlinVersion = "1.3.70"
    extra.set("kotlinVersion",kotlinVersion)
    repositories {
        google()
        jcenter()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:4.1.0-alpha09")
        classpath(kotlin("gradle-plugin", version = kotlinVersion) )
    }
}

allprojects {
    repositories {
        google()
        jcenter()
    }

}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
