package com.mycollab.rest.server.resource

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.flyway.FlywayAutoConfiguration
import org.springframework.boot.autoconfigure.freemarker.FreeMarkerAutoConfiguration
import org.springframework.boot.builder.SpringApplicationBuilder
import org.springframework.cloud.client.discovery.EnableDiscoveryClient

@SpringBootApplication(exclude = [FlywayAutoConfiguration::class, FreeMarkerAutoConfiguration::class],
        scanBasePackages = ["com.mycollab.**.spring", "com.mycollab.**.jobs, com.mycollab.**.configuration", "com.mycollab.rest.server.resource"])
@EnableDiscoveryClient
class Main

fun main(args: Array<String>) {
    SpringApplicationBuilder(Main::class.java).run(*args)
}