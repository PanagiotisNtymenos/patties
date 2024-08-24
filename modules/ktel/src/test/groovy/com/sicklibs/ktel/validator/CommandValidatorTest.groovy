package com.sicklibs.ktel.validator

import com.sicklibs.commonstest.helpers.UnitTest
import com.sicklibs.ktel.handler.CommandHandler

class CommandValidatorTest extends UnitTest {
  CommandValidations validations = Mock()
  CommandValidator validator

  def setup() {
    validator = new CommandValidator(validations)
  }

  def "Should validate commands and their handlers"() {
    given:
      Map<CommandHandler, Class> input = GroovyMock()
      Map<CommandHandler, Class> validatedInput = GroovyMock()

    when:
      Map<CommandHandler, Class> response = validator.validate(input)

    then:
      1 * validations.ensureCommandIsPresentForHandler(input) >> validatedInput
      1 * validations.ensureCommandIsNotLinkedToMultipleHandlers(validatedInput)
      0 * _

    and:
      response == validatedInput
  }
}
