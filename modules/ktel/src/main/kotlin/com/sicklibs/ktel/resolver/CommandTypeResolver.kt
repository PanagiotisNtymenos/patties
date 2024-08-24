package com.sicklibs.ktel.resolver

import com.sicklibs.ktel.command.Command
import com.sicklibs.ktel.handler.CommandHandler
import org.springframework.core.GenericTypeResolver.resolveTypeArguments
import org.springframework.stereotype.Component

@Component
internal class CommandTypeResolver<C : Command<R>, R> {

  companion object {
    private const val COMMAND_GENERIC_TYPE_INDEX = 0
  }

  fun resolve(handler: CommandHandler<C, R>): Class<*>? =
    resolveTypeArguments(handler::class.java, CommandHandler::class.java)
      ?.get(COMMAND_GENERIC_TYPE_INDEX)
}