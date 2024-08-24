package com.sicklibs.ktel.validator

import com.sicklibs.commons.utils.map.alsoIfNotEmpty
import com.sicklibs.commons.utils.map.filterDuplicateValues
import com.sicklibs.commons.utils.map.filterNullValues
import com.sicklibs.commons.utils.map.filterOutNullValues
import com.sicklibs.commons.utils.map.toPairs
import com.sicklibs.ktel.command.Command
import com.sicklibs.ktel.command.exception.CommandsLinkedToMultipleHandlersException
import com.sicklibs.ktel.command.exception.CommandsNotFoundException
import com.sicklibs.ktel.handler.CommandHandler
import org.springframework.stereotype.Component

@Component
internal class CommandValidations<C : Command<R>, R> {

  fun ensureCommandIsPresentForHandler(input: Map<CommandHandler<C, R>, Class<*>?>): Map<CommandHandler<C, R>, Class<*>> {
    input
      .filterNullValues()
      .alsoIfNotEmpty { throw CommandsNotFoundException(it.keys) }

    return input.filterOutNullValues()
  }

  fun ensureCommandIsNotLinkedToMultipleHandlers(input: Map<CommandHandler<C, R>, Class<*>>) {
    input
      .filterDuplicateValues()
      .alsoIfNotEmpty { throw CommandsLinkedToMultipleHandlersException(it.toPairs()) }
  }
}