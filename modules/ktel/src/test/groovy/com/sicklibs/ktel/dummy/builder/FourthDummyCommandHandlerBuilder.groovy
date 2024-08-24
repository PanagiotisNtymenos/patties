package com.sicklibs.ktel.dummy.builder

import com.sicklibs.ktel.dummy.handler.FourthDummyCommandHandler

class FourthDummyCommandHandlerBuilder {

  static FourthDummyCommandHandlerBuilder make() {
    new FourthDummyCommandHandlerBuilder()
  }

  FourthDummyCommandHandler build() {
    new FourthDummyCommandHandler()
  }
}
