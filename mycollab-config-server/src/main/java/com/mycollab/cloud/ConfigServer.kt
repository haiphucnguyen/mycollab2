package com.mycollab.cloud

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.builder.SpringApplicationBuilder
import org.springframework.cloud.config.server.EnableConfigServer

@SpringBootApplication
@EnableConfigServer
open class Main

fun main(args: Array<String>) {
    SpringApplicationBuilder(Main::class.java).run(*args)
}