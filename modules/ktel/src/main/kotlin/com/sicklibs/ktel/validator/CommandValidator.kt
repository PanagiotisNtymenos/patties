package com.sicklibs.ktel.validator

import com.sicklibs.ktel.command.Command
import com.sicklibs.ktel.handler.CommandHandler
import org.springframework.stereotype.Component

@Component
internal class CommandValidator<C : Command<R>, R>(
  private val validations: CommandValidations<C, R>
) {

  fun validate(input: Map<CommandHandler<C, R>, Class<*>?>): Map<CommandHandler<C, R>, Class<*>> {
    val validatedInput = validations.ensureCommandIsPresentForHandler(input)
    validations.ensureCommandIsNotLinkedToMultipleHandlers(validatedInput)
    return validatedInput
  }
}