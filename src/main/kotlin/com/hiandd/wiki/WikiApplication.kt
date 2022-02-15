package com.hiandd.wiki

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration
import org.springframework.boot.runApplication

@SpringBootApplication(exclude = [DataSourceAutoConfiguration::class])
class WikiApplication

fun main(args: Array<String>) {
	runApplication<WikiApplication>(*args)
}
