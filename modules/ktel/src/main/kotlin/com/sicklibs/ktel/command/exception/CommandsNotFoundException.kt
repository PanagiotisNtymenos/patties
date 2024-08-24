package com.sicklibs.ktel.command.exception

import com.sicklibs.ktel.handler.CommandHandler

data class CommandsNotFoundException(
  val commandHandlers: Set<CommandHandler<*, *>>
) : Exception(resolveMessage(commandHandlers)) {

  companion object {
    private const val MESSAGE_FOR_SINGLE_COMMAND_HANDLER = "Command not found for handler -> "
    private const val MESSAGE_FOR_MULTIPLE_COMMAND_HANDLERS = "Commands not found for handlers -> "

    private fun Set<CommandHandler<*, *>>.toJavaNames() =
      joinToString { it.javaClass.simpleName }

    private fun resolveMessage(commandHandlers: Set<CommandHandler<*, *>>): String =
      when (commandHandlers.size) {
        1 -> MESSAGE_FOR_SINGLE_COMMAND_HANDLER
        else -> MESSAGE_FOR_MULTIPLE_COMMAND_HANDLERS
      } + commandHandlers.toJavaNames()
  }
}

