package com.sicklibs.ktel

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class KtelApplication

fun main(args: Array<String>) {
  runApplication<KtelApplication>(*args)
}
