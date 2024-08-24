package com.sicklibs.ktel.handler.exception

import com.sicklibs.ktel.command.Command
import java.lang.Exception

data class CommandHandlerNotFoundException(
  val command: Command<*>
) : Exception("Command handler not found for command -> ${command.javaClass.simpleName}")
