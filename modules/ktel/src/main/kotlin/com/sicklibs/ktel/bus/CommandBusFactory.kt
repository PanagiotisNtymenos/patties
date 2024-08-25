package com.sicklibs.ktel.bus

import com.sicklibs.ktel.command.resolver.CommandResolver
import com.sicklibs.ktel.resolver.CommandHandlerResolver
import com.sicklibs.ktel.validator.CommandValidator
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component

@Component
internal class CommandBusFactory(
  private val applicationContext: ApplicationContext,
  private val commandResolver: CommandResolver,
  private val validator: CommandValidator
) {

  @Bean
  fun commandBus() =
    try {
      CommandBusImpl(
        CommandHandlerResolver(
          applicationContext,
          commandResolver,
          validator
        )
      )
    } catch (e: Exception) {
      throw e
    }
}