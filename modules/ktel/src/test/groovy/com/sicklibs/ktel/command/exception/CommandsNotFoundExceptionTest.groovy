package com.sicklibs.ktel.command.exception

import com.sicklibs.commonstest.helpers.UnitTest
import com.sicklibs.ktel.dummy.builder.FirstDummyCommandHandlerBuilder
import com.sicklibs.ktel.dummy.builder.SecondDummyCommandHandlerBuilder
import com.sicklibs.ktel.handler.CommandHandler

class CommandsNotFoundExceptionTest extends UnitTest {

  def "Should extend exception and throw when a command handler is not found"() {
    given:
      Set<CommandHandler> commandHandlers = [FirstDummyCommandHandlerBuilder.make().build()]

    when:
      Exception exception = new CommandsNotFoundException(commandHandlers)

    then:
      exception.commandHandlers == commandHandlers
      exception.message == "Command not found for handler -> FirstDummyCommandHandler"
  }

  def "Should extend exception and throw when multiple command handlers are not found"() {
    given:
      Set<CommandHandler> commandHandlers = [
        FirstDummyCommandHandlerBuilder.make().build(),
        SecondDummyCommandHandlerBuilder.make().build()
      ]

    when:
      Exception exception = new CommandsNotFoundException(commandHandlers)

    then:
      exception.commandHandlers == commandHandlers
      exception.message == "Commands not found for handlers -> FirstDummyCommandHandler, SecondDummyCommandHandler"
  }
}
