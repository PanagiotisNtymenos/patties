package com.sicklibs.ktel.resolver

import com.sicklibs.commons.utils.map.invert
import com.sicklibs.ktel.command.Command
import com.sicklibs.ktel.handler.CommandHandler
import com.sicklibs.ktel.handler.exception.CommandHandlerNotFoundException
import com.sicklibs.ktel.validator.CommandValidator
import jakarta.annotation.PostConstruct
import org.springframework.stereotype.Component

@Component
internal class CommandHandlerResolver(
  private val handlers: List<CommandHandler<Command<*>, *>>,
  private val commandTypeResolver: CommandTypeResolver,
  private val validator: CommandValidator
) {

  private var commandsWithHandlers: Map<Class<*>, CommandHandler<Command<*>, *>> = emptyMap()

  @PostConstruct
  private fun init() {
    commandsWithHandlers = handlers
      .associateWithCommands()
      .validate()
      .invert()
  }

  fun <C : Command<R>, R> resolve(command: C): CommandHandler<C, R> =
    commandsWithHandlers[command::class.java]
      ?.let { it as CommandHandler<C, R> }
      ?: throw CommandHandlerNotFoundException(command)

  private fun List<CommandHandler<Command<*>, *>>.associateWithCommands(): Map<CommandHandler<Command<*>, *>, Class<*>?> =
    associateWith { handler -> resolveCommandType(handler) }

  private fun Map<CommandHandler<Command<*>, *>, Class<*>?>.validate(): Map<CommandHandler<Command<*>, *>, Class<*>> =
    validator.validate(this)

  private fun resolveCommandType(handler: CommandHandler<Command<*>, *>): Class<*>? =
    commandTypeResolver.resolve(handler)
}