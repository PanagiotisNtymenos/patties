package com.sicklibs.ktel.handler.exception

import com.sicklibs.commonstest.helpers.UnitTest
import com.sicklibs.ktel.command.Command
import com.sicklibs.ktel.dummy.builder.FirstDummyCommandBuilder

class CommandHandlerNotFoundExceptionTest extends UnitTest {

  def "Should extend exception and throw when command handler is not found"() {
    given:
      Command command = FirstDummyCommandBuilder.make().build()

    when:
      Exception exception = new CommandHandlerNotFoundException(command)

    then:
      exception.command == command
      exception.message == "Command handler not found for command -> FirstDummyCommand"
  }
}
