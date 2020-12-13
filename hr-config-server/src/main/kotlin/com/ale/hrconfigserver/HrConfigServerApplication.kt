package com.ale.hrconfigserver

import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.config.server.EnableConfigServer

@EnableConfigServer
@SpringBootApplication
class HrConfigServerApplication

fun main(args: Array<String>) {
	runApplication<HrConfigServerApplication>(*args)
}
