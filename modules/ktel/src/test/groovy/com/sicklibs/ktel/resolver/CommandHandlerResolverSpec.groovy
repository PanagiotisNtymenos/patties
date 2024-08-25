package com.sicklibs.ktel.resolver

import com.sicklibs.commonstest.helpers.UnitTest
import com.sicklibs.ktel.command.Command
import com.sicklibs.ktel.command.resolver.CommandResolver
import com.sicklibs.ktel.dummy.builder.FirstDummyCommandBuilder
import com.sicklibs.ktel.dummy.handler.FirstDummyCommandHandler
import com.sicklibs.ktel.handler.CommandHandler
import com.sicklibs.ktel.handler.exception.CommandHandlerNotFoundException
import com.sicklibs.ktel.validator.CommandValidator
import org.springframework.context.ApplicationContext

class CommandHandlerResolverSpec extends UnitTest {
  FirstDummyCommandHandler handler = Mock()
  ApplicationContext applicationContext = Mock()
  CommandResolver commandResolver = Mock()
  CommandValidator validator = Mock()

  def "Should return the command handler that handles the given command"() {
    given:
      Command command = FirstDummyCommandBuilder.make().build()

    and:
      Map handlersWithCommands = [(handler): command.class]

    when:
      CommandHandler response = new CommandHandlerResolver(
        applicationContext,
        commandResolver,
        validator
      ).resolve(command)

    then:
      1 * applicationContext.getBeansOfType(CommandHandler) >> [(randomString()): handler]
      1 * commandResolver.resolve(handler) >> command.class
      1 * validator.validate(handlersWithCommands) >> handlersWithCommands
      0 * _

    and:
      response == handler
  }

  def "Should throw an exception when no command handler can handle the given command"() {
    given:
      Command command = GroovyMock()

    when:
      new CommandHandlerResolver(
        applicationContext,
        commandResolver,
        validator
      ).resolve(command)

    then:
      1 * applicationContext.getBeansOfType(CommandHandler) >> [:]
      1 * validator.validate([:]) >> [:]
      0 * _

    and:
      thrown(CommandHandlerNotFoundException)
  }
}
