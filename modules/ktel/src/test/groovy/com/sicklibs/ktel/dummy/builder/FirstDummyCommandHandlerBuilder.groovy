package com.sicklibs.ktel.dummy.builder

import com.sicklibs.ktel.dummy.handler.FirstDummyCommandHandler

class FirstDummyCommandHandlerBuilder {

  static FirstDummyCommandHandlerBuilder make() {
    new FirstDummyCommandHandlerBuilder()
  }

  FirstDummyCommandHandler build() {
    new FirstDummyCommandHandler()
  }
}
