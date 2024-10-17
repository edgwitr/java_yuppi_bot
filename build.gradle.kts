plugins {
    java
    application
}

application {
    mainClass.set("com.bot.App")
}
repositories {
    mavenCentral()
}
dependencies {
    implementation("net.dv8tion:JDA:5.1.2")
    implementation("io.github.cdimascio:dotenv-java:3.0.2")
}