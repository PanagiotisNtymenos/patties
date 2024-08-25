package com.sicklibs.ktel.bus

import com.sicklibs.commonstest.helpers.UnitTest
import com.sicklibs.ktel.command.resolver.CommandResolver
import com.sicklibs.ktel.handler.CommandHandler
import com.sicklibs.ktel.resolver.CommandHandlerResolver
import com.sicklibs.ktel.validator.CommandValidator
import org.springframework.context.ApplicationContext

class CommandBusFactoryTest extends UnitTest {
  ApplicationContext applicationContext = Mock()
  CommandResolver commandResolver = Mock()
  CommandValidator validator = Mock()
  CommandBusFactory factory

  def setup() {
    factory = new CommandBusFactory(
      applicationContext,
      commandResolver,
      validator
    )
  }

  def "Should create a command bus"() {
    when:
      CommandBus response = factory.commandBus()

    then:
      1 * applicationContext.getBeansOfType(CommandHandler) >> [:]
      1 * validator.validate([:]) >> [:]
      0 * _

    and:
      response instanceof CommandBusImpl

    and:
      CommandHandlerResolver handlerResolver = response.commandHandlerResolver
      handlerResolver.applicationContext == applicationContext
      handlerResolver.commandResolver == commandResolver
      handlerResolver.validator == validator
  }

  def "Should catch and throw any exception thrown during te command bus initialization"() {
    when:
      factory.commandBus()

    then:
      1 * applicationContext.getBeansOfType(CommandHandler) >> { throw new Exception() }
      0 * _

    then:
      thrown(Exception)
  }
}
