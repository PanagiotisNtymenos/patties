package com.sicklibs.ktel.validator

import com.sicklibs.commonstest.helpers.UnitTest
import com.sicklibs.ktel.command.exception.CommandsLinkedToMultipleHandlersException
import com.sicklibs.ktel.command.exception.CommandsNotFoundException
import com.sicklibs.ktel.dummy.builder.FirstDummyCommandHandlerBuilder
import com.sicklibs.ktel.dummy.builder.FourthDummyCommandHandlerBuilder
import com.sicklibs.ktel.dummy.builder.SecondDummyCommandHandlerBuilder
import com.sicklibs.ktel.dummy.builder.ThirdDummyCommandHandlerBuilder
import com.sicklibs.ktel.dummy.command.FirstDummyCommand
import com.sicklibs.ktel.dummy.command.SecondDummyCommand
import com.sicklibs.ktel.dummy.handler.FirstDummyCommandHandler
import com.sicklibs.ktel.dummy.handler.FourthDummyCommandHandler
import com.sicklibs.ktel.dummy.handler.SecondDummyCommandHandler
import com.sicklibs.ktel.dummy.handler.ThirdDummyCommandHandler
import com.sicklibs.ktel.handler.CommandHandler

class CommandValidationsTest extends UnitTest {
  CommandValidations validations

  def setup() {
    validations = new CommandValidations()
  }

  // ensureCommandIsPresentForHandler
  def "Should not throw when input is empty and ensureCommandIsPresentForHandler is invoked"() {
    when:
      Map response = validations.ensureCommandIsPresentForHandler([:])

    then:
      0 * _

    and:
      noExceptionThrown()

    and:
      response.isEmpty()
  }

  def "Should not throw when commands are present for all command handlers and ensureCommandIsPresentForHandler is invoked"() {
    given:
      FirstDummyCommandHandler commandHandler = FirstDummyCommandHandlerBuilder.make().build()
      Map<CommandHandler, Class> input = [(commandHandler): FirstDummyCommand]

    when:
      Map response = validations.ensureCommandIsPresentForHandler(input)

    then:
      0 * _

    and:
      noExceptionThrown()

    and:
      response == input
  }

  def "Should throw when command is not present for a command handler and ensureCommandIsPresentForHandler is invoked"() {
    given:
      FirstDummyCommandHandler commandHandler = FirstDummyCommandHandlerBuilder.make().build()
      Map<CommandHandler, Class> input = [(commandHandler): null]

    when:
      validations.ensureCommandIsPresentForHandler(input)

    then:
      0 * _

    and:
      thrown(CommandsNotFoundException)
  }

  def "Should throw when commands are not present for multiple command handlers and ensureCommandIsPresentForHandler is invoked"() {
    given:
      FirstDummyCommandHandler firstCommandHandler = FirstDummyCommandHandlerBuilder.make().build()
      SecondDummyCommandHandler secondCommandHandler = SecondDummyCommandHandlerBuilder.make().build()
      Map<CommandHandler, Class> input = [
        (firstCommandHandler) : null,
        (secondCommandHandler): null
      ]

    when:
      validations.ensureCommandIsPresentForHandler(input)

    then:
      0 * _

    and:
      thrown(CommandsNotFoundException)
  }

  // ensureCommandIsNotLinkedToMultipleHandlers
  def "Should not throw when input is empty and ensureCommandIsNotLinkedToMultipleHandlers is invoked"() {
    when:
      validations.ensureCommandIsNotLinkedToMultipleHandlers([:])

    then:
      0 * _

    and:
      noExceptionThrown()
  }

  def "Should not throw when each command is linked to a unique command handler and ensureCommandIsNotLinkedToMultipleHandlers is invoked"() {
    given:
      FirstDummyCommandHandler firstCommandHandler = FirstDummyCommandHandlerBuilder.make().build()
      SecondDummyCommandHandler secondCommandHandler = SecondDummyCommandHandlerBuilder.make().build()
      Map<CommandHandler, Class> input = [
        (firstCommandHandler) : FirstDummyCommand,
        (secondCommandHandler): SecondDummyCommand
      ]

    when:
      validations.ensureCommandIsNotLinkedToMultipleHandlers(input)

    then:
      0 * _

    and:
      noExceptionThrown()
  }

  def "Should throw when a command is linked to multiple command handlers and ensureCommandIsNotLinkedToMultipleHandlers is invoked"() {
    given:
      FirstDummyCommandHandler firstCommandHandler = FirstDummyCommandHandlerBuilder.make().build()
      SecondDummyCommandHandler secondCommandHandler = SecondDummyCommandHandlerBuilder.make().build()
      Map<CommandHandler, Class> input = [
        (firstCommandHandler) : FirstDummyCommand,
        (secondCommandHandler): FirstDummyCommand
      ]

    when:
      validations.ensureCommandIsNotLinkedToMultipleHandlers(input)

    then:
      0 * _

    and:
      thrown(CommandsLinkedToMultipleHandlersException)
  }

  def "Should throw when multiple commands are linked to multiple command handlers and ensureCommandIsNotLinkedToMultipleHandlers is invoked"() {
    given:
      FirstDummyCommandHandler firstCommandHandler = FirstDummyCommandHandlerBuilder.make().build()
      SecondDummyCommandHandler secondCommandHandler = SecondDummyCommandHandlerBuilder.make().build()
      ThirdDummyCommandHandler thirdCommandHandler = ThirdDummyCommandHandlerBuilder.make().build()
      FourthDummyCommandHandler fourthCommandHandler = FourthDummyCommandHandlerBuilder.make().build()
      Map<CommandHandler, Class> input = [
        (firstCommandHandler) : FirstDummyCommand,
        (secondCommandHandler): FirstDummyCommand,
        (thirdCommandHandler) : SecondDummyCommand,
        (fourthCommandHandler): SecondDummyCommand
      ]

    when:
      validations.ensureCommandIsNotLinkedToMultipleHandlers(input)

    then:
      0 * _

    and:
      thrown(CommandsLinkedToMultipleHandlersException)
  }
}
