package com.sicklibs.ktel.command.exception

import com.sicklibs.commonstest.helpers.UnitTest
import com.sicklibs.ktel.dummy.builder.FirstDummyCommandHandlerBuilder
import com.sicklibs.ktel.dummy.builder.FourthDummyCommandHandlerBuilder
import com.sicklibs.ktel.dummy.builder.SecondDummyCommandHandlerBuilder
import com.sicklibs.ktel.dummy.builder.ThirdDummyCommandHandlerBuilder
import com.sicklibs.ktel.dummy.command.FirstDummyCommand
import com.sicklibs.ktel.dummy.command.SecondDummyCommand
import com.sicklibs.ktel.handler.CommandHandler
import kotlin.Pair

class CommandsLinkedToMultipleHandlersExceptionTest extends UnitTest {

  def "Should extend exception and throw when a command is linked to multiple handlers"() {
    given:
      Class command = FirstDummyCommand
      CommandHandler firstHandler = FirstDummyCommandHandlerBuilder.make().build()
      CommandHandler secondHandler = SecondDummyCommandHandlerBuilder.make().build()

    and:
      List handlersToCommands = [new Pair(firstHandler, command), new Pair(secondHandler, command)]

    when:
      Exception exception = new CommandsLinkedToMultipleHandlersException(handlersToCommands)

    then:
      exception.handlersToCommands == handlersToCommands
      exception.message == "Command linked to multiple handlers: FirstDummyCommand -> (FirstDummyCommandHandler, SecondDummyCommandHandler)"
  }

  def "Should extend exception and throw when multiple commands are linked to multiple handlers"() {
    given:
      Class firstCommand = FirstDummyCommand
      Class secondCommand = SecondDummyCommand
      CommandHandler firstHandler = FirstDummyCommandHandlerBuilder.make().build()
      CommandHandler secondHandler = SecondDummyCommandHandlerBuilder.make().build()
      CommandHandler thirdHandler = ThirdDummyCommandHandlerBuilder.make().build()
      CommandHandler fourthHandler = FourthDummyCommandHandlerBuilder.make().build()

    and:
      List handlersToCommands = [
        new Pair(firstHandler, firstCommand),
        new Pair(secondHandler, firstCommand),
        new Pair(thirdHandler, secondCommand),
        new Pair(fourthHandler, secondCommand)
      ]

    when:
      Exception exception = new CommandsLinkedToMultipleHandlersException(handlersToCommands)

    then:
      exception.handlersToCommands == handlersToCommands
      exception.message == "Commands linked to multiple handlers: FirstDummyCommand -> (FirstDummyCommandHandler, SecondDummyCommandHandler), SecondDummyCommand -> (ThirdDummyCommandHandler, FourthDummyCommandHandler)"
  }
}
