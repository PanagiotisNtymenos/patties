package com.sicklibs.ktel.annotation

import org.springframework.context.annotation.ComponentScan
import kotlin.annotation.AnnotationRetention.RUNTIME
import kotlin.annotation.AnnotationTarget.CLASS

/**
 * [EnableKtel] is a meta-annotation that triggers the component scan for the Ktel module.
 */
@Target(CLASS)
@Retention(RUNTIME)
@ComponentScan("com.sicklibs.ktel")
annotation class EnableKtel
