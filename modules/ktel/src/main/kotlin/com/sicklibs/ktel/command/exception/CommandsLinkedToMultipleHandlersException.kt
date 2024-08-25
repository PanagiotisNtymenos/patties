package com.sicklibs.ktel.command.exception

import com.sicklibs.ktel.command.Command
import com.sicklibs.ktel.handler.CommandHandler

data class CommandsLinkedToMultipleHandlersException(
  val handlersToCommands: Map<CommandHandler<Command<*>, *>, Class<*>>
) : Exception(resolveMessage(handlersToCommands)) {

  companion object {
    private const val MESSAGE_FOR_SINGLE_COMMAND = "Command linked to multiple handlers: "
    private const val MESSAGE_FOR_MULTIPLE_COMMANDS = "Commands linked to multiple handlers: "

    private fun Map<CommandHandler<Command<*>, *>, Class<*>>.toJavaNames(): String =
      entries
        .groupBy { it.value }
        .map { (key, value) -> key.simpleName to "(${value.joinToString { it.key.javaClass.simpleName }})" }
        .joinToString { (key, value) -> "$key -> $value" }

    private fun resolveMessage(handlersToCommands: Map<CommandHandler<Command<*>, *>, Class<*>>): String =
      when (handlersToCommands.values.distinct().size) {
        1 -> MESSAGE_FOR_SINGLE_COMMAND
        else -> MESSAGE_FOR_MULTIPLE_COMMANDS
      } + handlersToCommands.toJavaNames()
  }
}
