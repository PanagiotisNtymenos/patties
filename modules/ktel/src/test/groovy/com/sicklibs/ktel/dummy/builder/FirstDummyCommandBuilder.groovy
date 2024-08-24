package com.sicklibs.ktel.dummy.builder

import com.sicklibs.commonstest.helpers.TestHelper
import com.sicklibs.ktel.dummy.command.FirstDummyCommand

class FirstDummyCommandBuilder implements TestHelper {
  private String attribute

  static FirstDummyCommandBuilder make() {
    new FirstDummyCommandBuilder().with {
      attribute = randomString()
      it
    }
  }

  FirstDummyCommand build() {
    new FirstDummyCommand(attribute)
  }
}
