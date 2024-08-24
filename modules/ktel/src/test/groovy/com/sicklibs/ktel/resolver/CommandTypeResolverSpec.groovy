package com.sicklibs.ktel.resolver

import com.sicklibs.commonstest.helpers.UnitTest
import com.sicklibs.ktel.dummy.command.FirstDummyCommand
import com.sicklibs.ktel.dummy.handler.FirstDummyCommandHandler
import com.sicklibs.ktel.handler.CommandHandler

class CommandTypeResolverSpec extends UnitTest {
  CommandTypeResolver resolver

  def setup() {
    resolver = new CommandTypeResolver()
  }

  def "Should resolve the command type"() {
    given:
      FirstDummyCommandHandler handler = GroovyMock()

    when:
      Class response = resolver.resolve(handler)

    then:
      response == FirstDummyCommand
  }

  def "Should return null when command type cannot be resolved"() {
    given:
      CommandHandler handler = GroovyMock()

    when:
      Class response = resolver.resolve(handler)

    then:
      !response
  }
}
