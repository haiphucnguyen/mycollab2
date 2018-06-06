package com.mycollab.ondemand.schedule

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.flyway.FlywayAutoConfiguration
import org.springframework.boot.autoconfigure.freemarker.FreeMarkerAutoConfiguration
import org.springframework.boot.builder.SpringApplicationBuilder
import org.springframework.scheduling.annotation.EnableScheduling

@SpringBootApplication(exclude = [FlywayAutoConfiguration::class, FreeMarkerAutoConfiguration::class])
@EnableScheduling
open class Main

fun main(args: Array<String>) {
    SpringApplicationBuilder(Main::class.java).web(false).run(*args)
}

