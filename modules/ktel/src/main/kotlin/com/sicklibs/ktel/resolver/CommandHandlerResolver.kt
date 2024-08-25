package com.sicklibs.ktel.resolver

import com.sicklibs.commons.utils.map.alsoIfNotEmpty
import com.sicklibs.commons.utils.map.invert
import com.sicklibs.ktel.bus.CommandBus
import com.sicklibs.ktel.command.Command
import com.sicklibs.ktel.handler.CommandHandler
import com.sicklibs.ktel.handler.exception.CommandHandlerNotFoundException
import com.sicklibs.ktel.validator.CommandValidator
import org.springframework.context.ApplicationContext
import org.springframework.stereotype.Component
import java.util.logging.Logger

@Component
internal class CommandHandlerResolver(
  private val applicationContext: ApplicationContext,
  private val commandTypeResolver: CommandTypeResolver,
  private val validator: CommandValidator
) {

  private var commandsWithHandlers: Map<Class<*>, CommandHandler<Command<*>, *>> = emptyMap()

  companion object {
    private val logger = Logger.getLogger(CommandBus::class.java.name)
  }

  init {
    commandsWithHandlers = getHandlers()
      .associateWithCommands()
      .validate()
      .invert()
      .alsoIfNotEmpty { logger.info("Command handlers registered successfully (${it.size})") }
  }

  @Suppress("UNCHECKED_CAST")
  fun <C : Command<R>, R> resolve(command: C): CommandHandler<C, R> =
    commandsWithHandlers[command::class.java]
      ?.let { it as CommandHandler<C, R> }
      ?: throw CommandHandlerNotFoundException(command)

  @Suppress("UNCHECKED_CAST")
  private fun getHandlers(): List<CommandHandler<Command<*>, *>> =
    applicationContext.getBeansOfType(CommandHandler::class.java).values.toList() as List<CommandHandler<Command<*>, *>>

  private fun List<CommandHandler<Command<*>, *>>.associateWithCommands(): Map<CommandHandler<Command<*>, *>, Class<*>?> =
    associateWith { handler -> resolveCommandType(handler) }

  private fun Map<CommandHandler<Command<*>, *>, Class<*>?>.validate(): Map<CommandHandler<Command<*>, *>, Class<*>> =
    validator.validate(this)

  private fun resolveCommandType(handler: CommandHandler<Command<*>, *>): Class<*>? =
    commandTypeResolver.resolve(handler)
}