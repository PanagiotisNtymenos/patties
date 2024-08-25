package com.sicklibs.ktel.bus

import com.sicklibs.ktel.resolver.CommandHandlerResolver
import com.sicklibs.ktel.command.Command
import org.springframework.stereotype.Component

@Component
internal class CommandBusImpl(
  private val commandHandlerResolver: CommandHandlerResolver
) : CommandBus {

  override fun <C : Command<R>, R> route(command: C): R =
    commandHandlerResolver.resolve(command)
      .handle(command)
}