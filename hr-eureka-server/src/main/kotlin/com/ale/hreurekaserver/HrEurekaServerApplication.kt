package com.ale.hreurekaserver

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer

@SpringBootApplication
@EnableEurekaServer
class HrEurekaServerApplication

fun main(args: Array<String>) {
	runApplication<HrEurekaServerApplication>(*args)
}
