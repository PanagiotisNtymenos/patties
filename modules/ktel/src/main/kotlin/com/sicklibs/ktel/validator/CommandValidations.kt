package com.sicklibs.ktel.validator

import com.sicklibs.commons.utils.map.alsoIfNotEmpty
import com.sicklibs.commons.utils.map.filterDuplicateValues
import com.sicklibs.commons.utils.map.filterNullValues
import com.sicklibs.commons.utils.map.filterOutNullValues
import com.sicklibs.ktel.command.Command
import com.sicklibs.ktel.command.exception.CommandsLinkedToMultipleHandlersException
import com.sicklibs.ktel.command.exception.CommandsNotFoundException
import com.sicklibs.ktel.handler.CommandHandler
import org.springframework.stereotype.Component

@Component
internal class CommandValidations {

  fun ensureCommandIsPresentForHandler(input: Map<CommandHandler<Command<*>, *>, Class<*>?>): Map<CommandHandler<Command<*>, *>, Class<*>> {
    input
      .filterNullValues()
      .alsoIfNotEmpty { throw CommandsNotFoundException(it.keys) }

    return input.filterOutNullValues()
  }

  fun ensureCommandIsNotLinkedToMultipleHandlers(input: Map<CommandHandler<Command<*>, *>, Class<*>>) {
    input
      .filterDuplicateValues()
      .alsoIfNotEmpty { throw CommandsLinkedToMultipleHandlersException(it) }
  }
}