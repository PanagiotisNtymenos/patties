package com.sicklibs.ktel.dummy.builder

import com.sicklibs.ktel.dummy.handler.ThirdDummyCommandHandler

class ThirdDummyCommandHandlerBuilder {

  static ThirdDummyCommandHandlerBuilder make() {
    new ThirdDummyCommandHandlerBuilder()
  }

  ThirdDummyCommandHandler build() {
    new ThirdDummyCommandHandler()
  }
}
