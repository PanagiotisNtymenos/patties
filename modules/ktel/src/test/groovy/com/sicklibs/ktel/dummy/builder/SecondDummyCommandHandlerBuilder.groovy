package com.sicklibs.ktel.dummy.builder

import com.sicklibs.ktel.dummy.handler.SecondDummyCommandHandler

class SecondDummyCommandHandlerBuilder {

  static SecondDummyCommandHandlerBuilder make() {
    new SecondDummyCommandHandlerBuilder()
  }

  SecondDummyCommandHandler build() {
    new SecondDummyCommandHandler()
  }
}
