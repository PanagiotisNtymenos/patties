package com.sicklibs.ktel.resolver

import com.sicklibs.commons.utils.map.invert
import com.sicklibs.ktel.command.Command
import com.sicklibs.ktel.handler.CommandHandler
import com.sicklibs.ktel.handler.exception.CommandHandlerNotFoundException
import com.sicklibs.ktel.validator.CommandValidator
import jakarta.annotation.PostConstruct
import org.springframework.stereotype.Component

@Component
internal class CommandHandlerResolver<C : Command<R>, R>(
  private val handlers: List<CommandHandler<C, R>>,
  private val commandTypeResolver: CommandTypeResolver<C, R>,
  private val validator: CommandValidator<C, R>
) {

  private var commandsWithHandlers: Map<Class<*>, CommandHandler<C, R>> = emptyMap()

  @PostConstruct
  private fun init() {
    commandsWithHandlers = handlers
      .associateWithCommands()
      .validate()
      .invert()
  }

  fun resolve(command: C): CommandHandler<C, R> =
    commandsWithHandlers[command::class.java]
      ?: throw CommandHandlerNotFoundException(command)

  private fun List<CommandHandler<C, R>>.associateWithCommands(): Map<CommandHandler<C, R>, Class<*>?> =
    associateWith { handler -> resolveCommandType(handler) }

  private fun Map<CommandHandler<C, R>, Class<*>?>.validate(): Map<CommandHandler<C, R>, Class<*>> =
    validator.validate(this)

  private fun resolveCommandType(handler: CommandHandler<C, R>): Class<*>? =
    commandTypeResolver.resolve(handler)
}