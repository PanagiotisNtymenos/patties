package com.sicklibs.ktel.resolver

import com.sicklibs.commonstest.helpers.UnitTest
import com.sicklibs.ktel.command.Command
import com.sicklibs.ktel.dummy.builder.FirstDummyCommandBuilder
import com.sicklibs.ktel.dummy.command.FirstDummyCommand
import com.sicklibs.ktel.dummy.handler.FirstDummyCommandHandler
import com.sicklibs.ktel.handler.CommandHandler
import com.sicklibs.ktel.handler.exception.CommandHandlerNotFoundException
import com.sicklibs.ktel.validator.CommandValidator

class CommandHandlerResolverSpec extends UnitTest {
  FirstDummyCommandHandler handler = GroovyMock()
  CommandTypeResolver commandTypeResolver = Mock()
  CommandValidator validator = Mock()
  CommandHandlerResolver resolver

  def setup() {
    resolver = new CommandHandlerResolver(
      [handler],
      commandTypeResolver,
      validator
    )
  }

  def "Should return the command handler that handles the given command"() {
    given:
      Command command = FirstDummyCommandBuilder.make().build()

    and:
      Map handlersWithCommands = [(handler): command.class]

    and:
      commandTypeResolver.resolve(handler) >> command.class
      validator.validate(handlersWithCommands) >> handlersWithCommands
      resolver.init()

    when:
      CommandHandler response = resolver.resolve(command)

    then:
      response == handler
  }

  def "Should throw an exception when no command handler can handle the given command"() {
    given:
      Command command = GroovyMock()

    when:
      resolver.resolve(command)

    then:
      thrown(CommandHandlerNotFoundException)
  }
}
