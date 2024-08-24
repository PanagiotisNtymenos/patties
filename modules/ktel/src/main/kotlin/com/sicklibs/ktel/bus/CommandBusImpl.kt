package com.sicklibs.ktel.bus

import com.sicklibs.ktel.resolver.CommandHandlerResolver
import com.sicklibs.ktel.command.Command
import org.springframework.stereotype.Component

@Component
internal class CommandBusImpl<C : Command<R>, R>(
  private val commandHandlerResolver: CommandHandlerResolver<C, R>
) : CommandBus<C, R> {

  override fun route(command: C): R =
    commandHandlerResolver.resolve(command)
      .handle(command)
}