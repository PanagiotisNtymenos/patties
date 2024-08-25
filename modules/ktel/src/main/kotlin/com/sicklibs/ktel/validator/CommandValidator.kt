package com.sicklibs.ktel.validator

import com.sicklibs.ktel.command.Command
import com.sicklibs.ktel.handler.CommandHandler
import org.springframework.stereotype.Component

@Component
internal class CommandValidator(
  private val validations: CommandValidations
) {

  fun validate(input: Map<CommandHandler<Command<*>, *>, Class<*>?>): Map<CommandHandler<Command<*>, *>, Class<*>> {
    val validatedInput = validations.ensureCommandIsPresentForHandler(input)
    validations.ensureCommandIsNotLinkedToMultipleHandlers(validatedInput)
    return validatedInput
  }
}