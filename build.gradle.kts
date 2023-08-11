plugins {
    id("java")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")

    implementation("com.google.code.gson:gson:2.10.1")

    implementation("com.sparkjava:spark-kotlin:1.0.0-alpha")
    implementation("com.sparkjava:spark-core:2.9.4")
    implementation("ch.qos.logback:logback-classic:1.2.6")
    implementation("com.j256.ormlite:ormlite-core:5.6") // Use the latest version available
    implementation("com.j256.ormlite:ormlite-jdbc:5.6") // Use the latest version available

    implementation("org.postgresql:postgresql:42.6.0") // Correct version for the PostgreSQL JDBC driver


}

tasks.test {
    useJUnitPlatform()
}