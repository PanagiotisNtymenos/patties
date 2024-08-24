package com.sicklibs.ktel.bus

import com.sicklibs.commonstest.helpers.UnitTest
import com.sicklibs.ktel.command.Command
import com.sicklibs.ktel.handler.CommandHandler
import com.sicklibs.ktel.resolver.CommandHandlerResolver

class CommandBusImplTest extends UnitTest {
  CommandHandlerResolver commandHandlerResolver = Mock()
  CommandBusImpl commandBus

  def setup() {
    commandBus = new CommandBusImpl(commandHandlerResolver)
  }

  def "Should resolve the handler and route the command"() {
    given:
      Command command = Mock()
      CommandHandler commandHandler = Mock()
      Object expected = GroovyMock()

    when:
      Object response = commandBus.route(command)

    then:
      1 * commandHandlerResolver.resolve(command) >> commandHandler
      1 * commandHandler.handle(command) >> expected
      0 * _

    and:
      response == expected
  }
}
