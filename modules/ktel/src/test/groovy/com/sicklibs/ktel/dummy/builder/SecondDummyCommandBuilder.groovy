package com.sicklibs.ktel.dummy.builder

import com.sicklibs.commonstest.helpers.TestHelper
import com.sicklibs.ktel.dummy.command.SecondDummyCommand

class SecondDummyCommandBuilder implements TestHelper {
  private String attribute

  static SecondDummyCommandBuilder make() {
    new SecondDummyCommandBuilder().with {
      attribute = randomString()
      it
    }
  }

  SecondDummyCommand build() {
    new SecondDummyCommand(attribute)
  }
}
